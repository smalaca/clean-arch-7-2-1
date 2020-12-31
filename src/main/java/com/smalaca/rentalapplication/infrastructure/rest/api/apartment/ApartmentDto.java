package com.smalaca.rentalapplication.infrastructure.rest.api.apartment;

import java.util.Map;

public class ApartmentDto {
    private final String ownerId;
    private final String street;
    private final String postalCode;
    private final String houseNumber;
    private final String apartmentNumber;
    private final String city;
    private final String country;
    private final String description;
    private final Map<String, Double> roomsDefinition;

    public ApartmentDto(
            String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
            String city, String country, String description, Map<String, Double> roomsDefinition) {
        this.ownerId = ownerId;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.country = country;
        this.description = description;
        this.roomsDefinition = roomsDefinition;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Double> getRoomsDefinition() {
        return roomsDefinition;
    }
}
