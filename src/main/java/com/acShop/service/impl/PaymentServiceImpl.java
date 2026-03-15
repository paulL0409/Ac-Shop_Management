package com.acShop.service.impl;

import com.acShop.mapper.OrderMapper;
import com.acShop.pojo.Order;
import com.acShop.pojo.PaymentResponse;
import com.acShop.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.stripe.exception.SignatureVerificationException;
import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderMapper orderMapper;
    @Value("${stripe.secret-key}")
    private String stripeSecretkey;
    @Value("${stripe.webhook-secret}")
    private String webhookSecret;
    @Override
    public Object createPayment(Long orderId) {
        try {
            Stripe.apiKey = stripeSecretkey;

            Order order = orderMapper.findById(orderId);
            if (order == null) {
                throw new RuntimeException("Order not found");
            }
            if (!"UNPAID".equals(order.getStatus())) {
                throw new RuntimeException("Order is not unpaid");
            }
            long amountInCents = convertToCents(order.getTotalAmount());
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(amountInCents)
                            .setCurrency("aud")
                            .putMetadata("orderId", String.valueOf(order.getId()))
                            .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            orderMapper.updateStripePaymentIntentId(orderId, paymentIntent.getId());
            return new PaymentResponse(
                    paymentIntent.getClientSecret(),
                    paymentIntent.getId()
            );
        }catch (StripeException e) {
            throw new RuntimeException("Failed to create payment intent: " + e.getMessage());
        }

    }

    @Override
    public void handleWebhook(String payload, String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("Invalid Stripe signature");
        }

        System.out.println("Webhook event type: " + event.getType());

        switch (event.getType()) {
            case "payment_intent.succeeded":{
                PaymentIntent paymentIntent = (PaymentIntent) event
                        .getDataObjectDeserializer()
                        .getObject()
                        .orElseThrow(() -> new RuntimeException("Cannot deserialize payment intent"));

                String paymentIntentId = paymentIntent.getId();
                System.out.println("paymentIntentId = " + paymentIntentId);

                Order order = orderMapper.findByPaymentIntentId(paymentIntentId);

                if (order != null) {
                    orderMapper.markAsPaid(order.getId());
                    System.out.println("Order marked as PAID: " + order.getId());
                } else {
                    System.out.println("Order not found for paymentIntentId: " + paymentIntentId);
                }
                break;
            }
            case "payment_intent.payment_failed":{
                PaymentIntent paymentIntent = (PaymentIntent) event
                        .getDataObjectDeserializer()
                        .getObject()
                        .orElseThrow(() -> new RuntimeException("Cannot deserialize payment intent"));

                String paymentIntentId = paymentIntent.getId();
                System.out.println("paymentIntentId = " + paymentIntentId);

                Order order = orderMapper.findByPaymentIntentId(paymentIntentId);

                if (order != null) {
                    orderMapper.markAsFailed(order.getId());
                    System.out.println("Order marked as FAILED: " + order.getId());
                } else {
                    System.out.println("Order not found for paymentIntentId: " + paymentIntentId);
                }
                break;
            }
            default:
                System.out.println("Unhandled event type: " + event.getType());
                break;
        }
    }

    private long convertToCents(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(100)).longValueExact();
    }

}
