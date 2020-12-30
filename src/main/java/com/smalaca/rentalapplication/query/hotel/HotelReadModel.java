package com.smalaca.rentalapplication.query.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "HOTEL")
public class HotelReadModel {
    @Id
    @GeneratedValue
    private UUID id;

    private String street;
    private String postalCode;
    private String buildingNumber;
    private String city;
    private String country;

    private HotelReadModel() {}

    public HotelReadModel(String street, String postalCode, String buildingNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    public String getId() {
        return id.toString();
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
