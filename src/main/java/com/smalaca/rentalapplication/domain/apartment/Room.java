package com.smalaca.rentalapplication.domain.apartment;

public class Room {
    private final String name;
    private final SquareMeter squareMeter;

    public Room(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
