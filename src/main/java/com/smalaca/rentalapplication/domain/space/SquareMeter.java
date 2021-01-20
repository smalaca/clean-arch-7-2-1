package com.smalaca.rentalapplication.domain.space;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateMethod")
class SquareMeter {
    private Double value;

    private SquareMeter() {}

    private SquareMeter(Double value) {
        this.value = value;
    }

    static SquareMeter of(Double value) {
        if (value <= 0) {
            throw new SquareMeterException();
        }

        return new SquareMeter(value);
    }

    private Double getValue() {
        return value;
    }

    private void setValue(Double value) {
        this.value = value;
    }
}
