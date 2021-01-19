package com.smalaca.rentalapplication.domain.money;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class Money {
    private BigDecimal value;

    private Money() {}

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money of(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            return new Money(price);
        }

        throw new NotAllowedMoneyValueException(price);
    }
}
