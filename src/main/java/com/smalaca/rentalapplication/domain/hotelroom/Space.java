package com.smalaca.rentalapplication.domain.hotelroom;

class Space {
    private final String name;
    private final SquareMeter squareMeter;

    Space(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
