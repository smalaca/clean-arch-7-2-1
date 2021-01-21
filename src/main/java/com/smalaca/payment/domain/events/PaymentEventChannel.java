package com.smalaca.payment.domain.events;

import com.smalaca.payment.domain.payment.PaymentCompleted;
import com.smalaca.payment.domain.payment.PaymentFailed;

public interface PaymentEventChannel {
    void publish(PaymentCompleted paymentCompleted);

    void publish(PaymentFailed paymentFailed);
}
