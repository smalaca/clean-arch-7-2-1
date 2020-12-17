package com.smalaca.rentalapplication.domain.hotel;

public class Hotel {
    private final String name;
    private final Address address;

    Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
