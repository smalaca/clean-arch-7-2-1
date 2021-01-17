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

    public HotelAssertion hasOnlyOneHotelRoom(Consumer<HotelRoom> requirements) {
        hasHotelRooms(1);
        return hasHotelRoom(requirements);
    }

    public HotelAssertion hasHotelRoom(Consumer<HotelRoom> requirements) {
        Assertions.assertThat(actual).extracting("hotelRooms").satisfies(rooms -> {
            Assertions.assertThat((List<HotelRoom>) rooms).anySatisfy(requirements);
        });

        return this;
    }

    public HotelAssertion hasHotelRooms(int expected) {
        Assertions.assertThat(actual).extracting("hotelRooms").satisfies(rooms -> {
            Assertions.assertThat((List<HotelRoom>) rooms).hasSize(expected);
        });

        return this;
    }
}