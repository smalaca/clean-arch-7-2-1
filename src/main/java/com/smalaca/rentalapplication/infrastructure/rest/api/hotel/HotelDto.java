package com.smalaca.rentalapplication.infrastructure.rest.api.hotel;

class HotelDto {
    private final String name;
    private final String street;
    private final String postalCode;
    private final String buildingNumber;
    private final String city;
    private final String country;

    HotelDto(String name, String street, String postalCode, String buildingNumber, String city, String country) {
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    String getName() {
        return name;
    }

    String getStreet() {
        return street;
    }

    String getPostalCode() {
        return postalCode;
    }

    String getBuildingNumber() {
        return buildingNumber;
    }

    String getCity() {
        return city;
    }

    String getCountry() {
        return country;
    }
}
