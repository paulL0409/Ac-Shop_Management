package com.acShop.service;

public interface PaymentService {
    Object createPayment(Long orderId);

    void handleWebhook(String payload, String sigHeader);
}
