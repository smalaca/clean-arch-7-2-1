package com.smalaca.rentalapplication.infrastructure.rest.api.apartment;

import java.util.Map;

class ApartmentDto {
    private final String ownerId;
    private final String street;
    private final String postalCode;
    private final String houseNumber;
    private final String apartmentNumber;
    private final String city;
    private final String country;
    private final String description;
    private final Map<String, Double> roomsDefinition;

    ApartmentDto(
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

    String getOwnerId() {
        return ownerId;
    }

    String getStreet() {
        return street;
    }

    String getPostalCode() {
        return postalCode;
    }

    String getHouseNumber() {
        return houseNumber;
    }

    String getApartmentNumber() {
        return apartmentNumber;
    }

    String getCity() {
        return city;
    }

    String getCountry() {
        return country;
    }

    String getDescription() {
        return description;
    }

    Map<String, Double> getRoomsDefinition() {
        return roomsDefinition;
    }
}
