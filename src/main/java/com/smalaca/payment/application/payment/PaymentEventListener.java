package com.smalaca.payment.application.payment;

import com.smalaca.payment.domain.payment.Payment;
import com.smalaca.payment.domain.payment.PaymentFactory;
import com.smalaca.rentalapplication.domain.agreement.AgreementAccepted;

public class PaymentEventListener {
    private final PaymentFactory paymentFactory;

    PaymentEventListener(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    void consume(AgreementAccepted agreementAccepted) {
        Payment payment = paymentFactory
                .create(agreementAccepted.getTenantId(), agreementAccepted.getOwnerId(), agreementAccepted.getDays(), agreementAccepted.getPrice());

        payment.pay();
    }
}
