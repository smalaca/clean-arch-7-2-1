package com.smalaca.rentalapplication.domain.apartment;

public class Apartment {
    private final String ownerId;
    private final Address address;
    private final String description;

    Apartment(String ownerId, Address address, String description) {
        this.ownerId = ownerId;
        this.address = address;
        this.description = description;
    }
}
