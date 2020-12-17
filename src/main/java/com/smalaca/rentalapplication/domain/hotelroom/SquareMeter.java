package com.smalaca.rentalapplication.domain.hotelroom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private final Double value;

    SquareMeter(Double value) {
        this.value = value;
    }
}
