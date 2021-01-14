package com.smalaca.rentalapplication.domain.hotel;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.Consumer;

public class HotelAssertion {
    private final Hotel actual;

    private HotelAssertion(Hotel actual) {
        this.actual = actual;
    }

    public static HotelAssertion assertThat(Hotel actual) {
        return new HotelAssertion(actual);
    }

    public HotelAssertion hasNameEqualsTo(String name) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("name", name);
        return this;
    }

    public HotelAssertion hasAddressEqualsTo(String street, String postalCode, String buildingNumber, String city, String country) {
        Assertions.assertThat(actual).extracting("address")
                .hasFieldOrPropertyWithValue("street", street)
                .hasFieldOrPropertyWithValue("postalCode", postalCode)
                .hasFieldOrPropertyWithValue("buildingNumber", buildingNumber)
                .hasFieldOrPropertyWithValue("city", city)
                .hasFieldOrPropertyWithValue("country", country);
        return this;
    }

    public HotelAssertion hasOnlyOneHotelRoom(Consumer<HotelRoom> requirements) {
        Assertions.assertThat(actual).extracting("hotelRooms").satisfies(rooms -> {
            Assertions.assertThat((List<HotelRoom>) rooms)
                    .hasSize(1)
                    .allSatisfy(requirements);
        });

        return this;
    }
}