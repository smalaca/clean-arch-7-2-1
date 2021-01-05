package com.smalaca.rentalapplication.domain.hotel;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class Address {
    private String street;
    private String postalCode;
    private String buildingNumber;
    private String city;
    private String country;

    private Address() {}

    Address(String street, String postalCode, String buildingNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }
}
