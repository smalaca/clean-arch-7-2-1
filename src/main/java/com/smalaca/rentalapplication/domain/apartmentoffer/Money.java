package com.smalaca.rentalapplication.domain.apartmentoffer;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
    private BigDecimal value;

    private Money() {}

    private Money(BigDecimal value) {
        this.value = value;
    }

    static Money of(BigDecimal price) {
        if (isHigherThanZero(price)) {
            return new Money(price);
        } else {
            throw new NotAllowedMoneyValueException(price);
        }
    }

    private static boolean isHigherThanZero(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) >= 0;
    }
}
