package com.smalaca.payment.infrastructure.paymentservice;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
class PaymentRequest {
    private final String senderId;
    private final String recipientId;
    private final BigDecimal amount;
}
