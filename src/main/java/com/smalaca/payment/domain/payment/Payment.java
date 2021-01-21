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

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public void pay() {
        PaymentStatus paymentStatus = paymentService.transfer(senderId, recipientId, totalAmount);

        switch (paymentStatus) {
            case SUCCESS:
                paymentEventsPublisher.paymentCompleted(senderId, recipientId, totalAmount);
                break;
            case NOT_ENOUGH_MONEY:
                paymentEventsPublisher.paymentFailed(senderId, recipientId, totalAmount);
                break;
        }
    }

}
