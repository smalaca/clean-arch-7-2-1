package com.smalaca.rentalapplication.domain.apartment;

import java.util.List;

public class Apartment {
    private final String ownerId;
    private final Address address;
    private final List<Room> rooms;
    private final String description;

    Apartment(String ownerId, Address address, List<Room> rooms, String description) {
        this.ownerId = ownerId;
        this.address = address;
        this.rooms = rooms;
        this.description = description;
    }
}
