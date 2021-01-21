package com.smalaca.payment.infrastructure.paymentservice;

import com.smalaca.payment.domain.payment.PaymentService;
import com.smalaca.payment.domain.payment.PaymentStatus;

import java.math.BigDecimal;

class RestPaymentClient implements PaymentService {
    @Override
    public PaymentStatus transfer(String senderId, String recipientId, BigDecimal amount) {
        return PaymentStatus.SUCCESS;
    }
}
