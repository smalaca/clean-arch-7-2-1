package com.smalaca.payment.domain.payment;

import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

public class PaymentCompletedAssertion {
    private final PaymentCompleted actual;

    private PaymentCompletedAssertion(PaymentCompleted actual) {
        this.actual = actual;
    }

    public static PaymentCompletedAssertion assertThat(PaymentCompleted actual) {
        return new PaymentCompletedAssertion(actual);
    }

    public PaymentCompletedAssertion hasSenderId(String expected) {
        Assertions.assertThat(actual.getSenderId()).isEqualTo(expected);
        return this;
    }

    public PaymentCompletedAssertion hasRecipientId(String expected) {
        Assertions.assertThat(actual.getRecipientId()).isEqualTo(expected);
        return this;
    }

    public PaymentCompletedAssertion hasAmount(BigDecimal expected) {
        Assertions.assertThat(actual.getAmount()).isEqualTo(expected);
        return this;
    }
}
