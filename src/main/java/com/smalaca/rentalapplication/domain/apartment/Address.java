package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;

@Embeddable
class Address {
    private String street;
    private String postalCode;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String country;

    private Address() {}

    Address(String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.country = country;
    }
}
