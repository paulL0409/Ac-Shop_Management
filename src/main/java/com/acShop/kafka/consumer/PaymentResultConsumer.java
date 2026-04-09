package com.acShop.kafka.consumer;


import com.acShop.kafka.event.PaymentResultEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentResultConsumer {

    @KafkaListener(topics = "payment-succeeded", groupId = "notification-group")
    public void handlePaymentSuccess(PaymentResultEvent paymentResultEvent) {
        log.info("Received payment success event: " + paymentResultEvent.getOrderId());
    }

    @KafkaListener(topics = "payment-failed", groupId = "notification-group")
    public void handlePaymentFailed(PaymentResultEvent paymentResultEvent) {
        log.info("Received payment failed event: " + paymentResultEvent.getOrderId());
    }
}
