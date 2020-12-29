package com.smalaca.rentalapplication.query.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "HOTEL")
public class HotelReadModel {
    @Id
    @GeneratedValue
    private String id;

    private final String street;
    private final String postalCode;
    private final String buildingNumber;
    private final String city;
    private final String country;

    public HotelReadModel(
            String id, String street, String postalCode, String buildingNumber, String city, String country) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
