package com.smalaca.rentalapplication.domain.apartment;

import java.util.Map;

public class ApartmentTestBuilder {
    private final Apartment.Builder apartment;

    private ApartmentTestBuilder(Apartment.Builder apartment) {
        this.apartment = apartment;
    }
    
    public static ApartmentTestBuilder apartment() {
        return new ApartmentTestBuilder(Apartment.Builder.apartment());
    }

    public ApartmentTestBuilder withOwnerId(String ownerId) {
        apartment.withOwnerId(ownerId);
        return this;
    }

    public ApartmentTestBuilder withStreet(String street) {
        apartment.withStreet(street);
        return this;
    }

    public ApartmentTestBuilder withPostalCode(String postalCode) {
        apartment.withPostalCode(postalCode);
        return this;
    }

    public ApartmentTestBuilder withHouseNumber(String houseNumber) {
        apartment.withHouseNumber(houseNumber);
        return this;
    }

    public ApartmentTestBuilder withApartmentNumber(String apartmentNumber) {
        apartment.withApartmentNumber(apartmentNumber);
        return this;
    }

    public ApartmentTestBuilder withCity(String city) {
        apartment.withCity(city);
        return this;
    }

    public ApartmentTestBuilder withCountry(String country) {
        apartment.withCountry(country);
        return this;
    }

    public ApartmentTestBuilder withDescription(String description) {
        apartment.withDescription(description);
        return this;
    }

    public ApartmentTestBuilder withSpacesDefinition(Map<String, Double> spacesDefinition) {
        apartment.withSpacesDefinition(spacesDefinition);
        return this;
    }

    public Apartment build() {
        return apartment.build();
    }
}
