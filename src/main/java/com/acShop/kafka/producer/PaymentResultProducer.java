package com.acShop.kafka.producer;

import com.acShop.kafka.event.PaymentResultEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentResultProducer {
    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;
    public PaymentResultProducer(KafkaTemplate<String, PaymentResultEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentSucceeded(PaymentResultEvent paymentResultEvent) {
        kafkaTemplate.send("payment-succeeded", String.valueOf(paymentResultEvent.getOrderId()),  paymentResultEvent);
    }

    public void  sendPaymentFailed(PaymentResultEvent paymentResultEvent) {
        kafkaTemplate.send("payment-failed", String.valueOf(paymentResultEvent.getOrderId()),  paymentResultEvent);
    }
}
