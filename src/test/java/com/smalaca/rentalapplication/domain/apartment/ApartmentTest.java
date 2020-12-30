package com.smalaca.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ApartmentTest {
    @Test
    void shouldCreateApartmentWithAllRequiredFields() {
        String ownerId = "1234";
        String street = "Florianska";
        String postalCode = "12-345";
        String houseNumber = "1";
        String apartmentNumber = "13";
        String city = "Cracow";
        String country = "Poland";
        String description = "Nice place to stay";
        String room1Name = "Toilet";
        Double room1Size = 10.0;
        String room2Name = "Bedroom";
        Double room2Size = 30.0;
        Map<String, Double> roomsDefinition = ImmutableMap.of(
                room1Name, room1Size, room2Name, room2Size);

        Apartment actual = new ApartmentFactory().create(
                ownerId, street, postalCode, houseNumber, apartmentNumber, city, country,
                description, roomsDefinition);

        assertThatHasOwnerId(actual, ownerId);
        assertThatHasDescription(actual, description);
        assertThatHasAddress(actual, street, postalCode, houseNumber, apartmentNumber, city, country);
        assertThatHasRooms(actual, roomsDefinition);
    }

    private void assertThatHasOwnerId(Apartment actual, String ownerId) {

    }

    private void assertThatHasDescription(Apartment actual, String description) {

    }

    private void assertThatHasAddress(
            Apartment actual, String street, String postalCode, String houseNumber,
            String apartmentNumber, String city, String country) {

    }

    private void assertThatHasRooms(Apartment actual, Map<String, Double> roomsDefinition) {

    }
}