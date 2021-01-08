package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.math.BigDecimal;

class Money {
    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value;
    }

    static Money of(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            return new Money(price);
        } else {
            throw new NotAllowedMoneyValueException(price);
        }
    }
}
