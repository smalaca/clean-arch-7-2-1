package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
class Room {
    private final String name;

    @Embedded
    private final SquareMeter squareMeter;

    Room(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
