package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.math.BigDecimal;

public class NotAllowedMoneyValueException extends RuntimeException {
    NotAllowedMoneyValueException(BigDecimal price) {
        super("Price " + price + " is not greater than zero.");
    }
}
