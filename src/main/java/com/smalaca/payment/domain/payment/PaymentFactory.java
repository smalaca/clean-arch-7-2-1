package com.smalaca.payment.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PaymentFactory {
    private final PaymentService paymentService;
    private final PaymentEventsPublisher paymentEventsPublisher;

    public PaymentFactory(PaymentService paymentService, PaymentEventsPublisher paymentEventsPublisher) {
        this.paymentService = paymentService;
        this.paymentEventsPublisher = paymentEventsPublisher;
    }

    public Payment create(String senderId, String recipientId, List<LocalDate> days, BigDecimal amount) {
        return new Payment(paymentService, paymentEventsPublisher, senderId, recipientId, totalAmount(days, amount));
    }

    private BigDecimal totalAmount(List<LocalDate> days, BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(days.size()));
    }
}
