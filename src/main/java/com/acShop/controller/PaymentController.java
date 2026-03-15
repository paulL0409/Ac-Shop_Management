package com.acShop.controller;

import com.acShop.pojo.PaymentRequest;
import com.acShop.pojo.Result;
import com.acShop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @PostMapping("/create")
    public Result create(@RequestBody PaymentRequest payment){
        return Result.success(paymentService.createPayment(payment.getOrderId()));
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) {
        paymentService.handleWebhook(payload, sigHeader);
        return ResponseEntity.ok("Webhook received");
    }
}
