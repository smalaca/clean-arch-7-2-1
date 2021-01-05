package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class Room {
    private String name;

    @Embedded
    private SquareMeter squareMeter;

    private Room() {}

    Room(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
