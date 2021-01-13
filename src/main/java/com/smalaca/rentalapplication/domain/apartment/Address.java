package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class Address {
    private String street;
    private String postalCode;
    private String houseNumber;
    private String city;
    private String country;

    private Address() {}

    Address(String street, String postalCode, String houseNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }
}
