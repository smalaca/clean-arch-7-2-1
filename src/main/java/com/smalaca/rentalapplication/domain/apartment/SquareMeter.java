package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double size;

    SquareMeter(Double size) {
        this.size = size;
    }

    private Double getSize() {
        return size;
    }

    private void setSize(Double size) {
        this.size = size;
    }
}
