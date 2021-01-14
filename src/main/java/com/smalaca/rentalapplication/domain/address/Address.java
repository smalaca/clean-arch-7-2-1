package com.smalaca.rentalapplication.domain.address;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
@EqualsAndHashCode
public class Address {
    private String street;
    private String postalCode;
    private String buildingNumber;
    private String city;
    private String country;

    private Address() {}

    public Address(String street, String postalCode, String buildingNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }
}
