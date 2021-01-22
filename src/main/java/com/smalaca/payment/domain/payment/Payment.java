package com.smalaca.payment.domain.payment;

import java.math.BigDecimal;

public class Payment {
    private final PaymentService paymentService;
    private final PaymentEventsPublisher paymentEventsPublisher;
    private final String senderId;
    private final String recipientId;
    private final BigDecimal totalAmount;

    Payment(PaymentService paymentService, PaymentEventsPublisher paymentEventsPublisher, String senderId, String recipientId, BigDecimal totalAmount) {
        this.paymentService = paymentService;
        this.paymentEventsPublisher = paymentEventsPublisher;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.totalAmount = totalAmount;
    }

    public void pay() {
        paymentEventsPublisher.publish(paymentStatus(), senderId, recipientId, totalAmount);
    }

    private PaymentStatus paymentStatus() {
        return paymentService.transfer(senderId, recipientId, totalAmount);
    }
}
