package com.smalaca.rentalapplication.domain.hotelroom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double value;

    private SquareMeter() {}

    SquareMeter(Double value) {
        this.value = value;
    }

    private Double getValue() {
        return value;
    }

    private void setValue(Double value) {
        this.value = value;
    }
}
