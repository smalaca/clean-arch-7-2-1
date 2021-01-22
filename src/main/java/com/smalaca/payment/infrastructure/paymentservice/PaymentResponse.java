package com.smalaca.payment.infrastructure.paymentservice;

import com.google.common.collect.ImmutableMap;
import com.smalaca.payment.domain.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class PaymentResponse {
    private static final Map<String, PaymentStatus> STATUSES = ImmutableMap.of(
            "NOT_ENOUGH_RESOURCES", PaymentStatus.NOT_ENOUGH_MONEY,
            "SUCCESS", PaymentStatus.SUCCESS);

    private String status;

    PaymentStatus paymentStatus() {
        return STATUSES.get(status);
    }
}
