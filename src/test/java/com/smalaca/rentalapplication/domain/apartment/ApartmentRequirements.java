package com.smalaca.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ApartmentRequirements {
    private static final Map<String, Double> SPACES_TO_FULFIL_CONTRACT = ImmutableMap.of("RoomXYZ", 123.45);

    private final ApartmentTestBuilder apartment;

    private ApartmentRequirements(ApartmentTestBuilder apartment) {
        this.apartment = apartment;
    }
    
    public static ApartmentRequirements apartment() {
        return new ApartmentRequirements(ApartmentTestBuilder.apartment());
    }

    public ApartmentRequirements withOwnerId(String ownerId) {
        apartment.withOwnerId(ownerId);
        return this;
    }

    public ApartmentRequirements withApartmentNumber(String apartmentNumber) {
        apartment.withApartmentNumber(apartmentNumber);
        return this;
    }

    public ApartmentRequirements withAddress(String street, String postalCode, String houseNumber, String city, String country) {
        apartment
                .withStreet(street)
                .withPostalCode(postalCode)
                .withHouseNumber(houseNumber)
                .withCity(city)
                .withCountry(country);
        return this;
    }

    Apartment get() {
        return apartment
                .withSpacesDefinition(SPACES_TO_FULFIL_CONTRACT)
                .build();
    }
}
