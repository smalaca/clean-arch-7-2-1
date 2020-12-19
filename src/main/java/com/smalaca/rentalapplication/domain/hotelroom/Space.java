package com.smalaca.rentalapplication.domain.hotelroom;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL_ROOM_SPACE")
class Space {
    private final String name;

    @Embedded
    private final SquareMeter squareMeter;

    Space(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
