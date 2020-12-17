package com.smalaca.rentalapplication.domain.hotel;

import javax.persistence.Embeddable;

@Embeddable
class Address {
    private final String street;
    private final String postalCode;
    private final String buildingNumber;
    private final String city;
    private final String country;

    Address(String street, String postalCode, String buildingNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }
}
