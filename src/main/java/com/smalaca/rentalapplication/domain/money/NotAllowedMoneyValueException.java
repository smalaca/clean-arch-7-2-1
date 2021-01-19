package com.smalaca.rentalapplication.domain.money;

import java.math.BigDecimal;

public class NotAllowedMoneyValueException extends RuntimeException {
    NotAllowedMoneyValueException(BigDecimal price) {
        super("Price " + price + " is not greater than zero.");
    }
}
