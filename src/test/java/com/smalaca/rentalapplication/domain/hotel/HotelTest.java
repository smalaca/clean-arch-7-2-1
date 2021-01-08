package com.smalaca.rentalapplication.domain.hotel;

import org.junit.jupiter.api.Test;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static com.smalaca.rentalapplication.domain.hotel.HotelAssertion.assertThat;

class HotelTest {
    private static final String NAME = "Great hotel";
    private static final String STREET = "Unknown";
    private static final String POSTAL_CODE = "12-345";
    private static final String BUILDING_NUMBER = "13";
    private static final String CITY = "Somewhere";
    private static final String COUNTRY = "Nowhere";

    @Test
    void shouldCreateHotelWithAllInformation() {
        Hotel actual = hotel()
                .withName(NAME)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withBuildingNumber(BUILDING_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();

        assertThat(actual)
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);
    }
}