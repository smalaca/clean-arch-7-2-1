package com.smalaca.payment.infrastructure.paymentservice;

import com.smalaca.payment.domain.payment.PaymentService;
import com.smalaca.payment.domain.payment.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RestPaymentClient implements PaymentService {
    private PaymentStatus paymentStatus = PaymentStatus.SUCCESS;

    @Override
    public PaymentStatus transfer(String senderId, String recipientId, BigDecimal amount) {
        return paymentStatus;
    }

    public void change(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
