package com.smalaca.rentalapplication.domain.hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static org.assertj.core.api.Assertions.assertThat;

class HotelTest {
    private static final String NAME_1 = "Great hotel";
    private static final String STREET_1 = "Florianska";
    private static final String POSTAL_CODE_1 = "12-345";
    private static final String BUILDING_NUMBER_1 = "1";
    private static final String CITY_1 = "Cracow";
    private static final String COUNTRY_1 = "Poland";
    private static final String NAME_2 = "Even greater hotel";
    private static final String STREET_2 = "Grodzka";
    private static final String POSTAL_CODE_2 = "54-321";
    private static final String BUILDING_NUMBER_2 = "13";
    private static final String CITY_2 = "Berlin";
    private static final String COUNTRY_2 = "Germany";

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        Hotel actual = givenHotel();

        assertThat(actual.equals(actual)).isTrue();
        assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoHotelInstancesRepresentTheSameAggregate() {
        Hotel toCompare = givenHotel();

        Hotel actual = givenHotel();

        assertThat(actual.equals(toCompare)).isTrue();
        assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsHotel() {
        Hotel actual = givenHotel();

        assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTheSameHotels")
    void shouldRecognizeHotelDoesNotRepresentTheSameAggregate(Object toCompare) {
        Hotel actual = givenHotel();

        assertThat(actual.equals(toCompare)).isFalse();
        assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private Hotel givenHotel() {
        return givenHotelBuilder().build();
    }

    private static Stream<Object> notTheSameHotels() {
        return Stream.of(
                givenHotelBuilder().withName(NAME_2).build(),
                givenHotelBuilder().withStreet(STREET_2).build(),
                givenHotelBuilder().withPostalCode(POSTAL_CODE_2).build(),
                givenHotelBuilder().withBuildingNumber(BUILDING_NUMBER_2).build(),
                givenHotelBuilder().withCity(CITY_2).build(),
                givenHotelBuilder().withCountry(COUNTRY_2).build(),
                new Object()
        );
    }

    private static Hotel.Builder givenHotelBuilder() {
        return hotel()
                .withName(NAME_1)
                .withStreet(STREET_1)
                .withPostalCode(POSTAL_CODE_1)
                .withBuildingNumber(BUILDING_NUMBER_1)
                .withCity(CITY_1)
                .withCountry(COUNTRY_1);
    }
}