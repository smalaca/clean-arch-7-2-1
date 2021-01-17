package com.smalaca.rentalapplication.domain.hotelroomoffer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

class HotelRoomOfferTest {
    private static final String HOTEL_ID_1 = "1234";
    private static final int HOTEL_ROOM_NUMBER_1 = 42;
    private static final BigDecimal PRICE_1 = BigDecimal.valueOf(123.45);
    private static final LocalDate START_1 = LocalDate.of(2040, 10, 11);
    private static final LocalDate END_1 = LocalDate.of(2040, 10, 20);
    private static final String HOTEL_ID_2 = "3453";
    private static final BigDecimal PRICE_2 = BigDecimal.valueOf(678.91);
    private static final LocalDate START_2 = LocalDate.of(2040, 10, 30);
    private static final LocalDate END_2 = LocalDate.of(2040, 11, 21);
    private static final int HOTEL_ROOM_NUMBER_2 = 98;

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        HotelRoomOffer actual = givenHotelRoomOffer();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfHotelRoomOfferRepresentsTheSameAggregate() {
        HotelRoomOffer toCompare = givenHotelRoomOffer();

        HotelRoomOffer actual = givenHotelRoomOffer();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfHotelRoomOfferRepresentsTheSameAggregateWhenOnlyHotelIdAndHotelRoomNumberAreTheSame() {
        HotelRoomOffer toCompare = hotelRoomOffer()
                .withHotelId(HOTEL_ID_1)
                .withHotelRoomNumber(HOTEL_ROOM_NUMBER_1)
                .withPrice(PRICE_2)
                .withAvailability(START_2, END_2)
                .build();

        HotelRoomOffer actual = givenHotelRoomOffer();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsHotelRoomOffer() {
        HotelRoomOffer actual = givenHotelRoomOffer();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTeSameHotelRoomOffers")
    void shouldRecognizeHotelRoomOffersDoesNotRepresentTheSameAggregate(Object toCompare) {
        HotelRoomOffer actual = givenHotelRoomOffer();

        Assertions.assertThat(actual.equals(toCompare)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private static Stream<Object> notTeSameHotelRoomOffers() {
        return Stream.of(
                hotelRoomOffer()
                        .withHotelId(HOTEL_ID_2)
                        .withHotelRoomNumber(HOTEL_ROOM_NUMBER_1)
                        .withPrice(PRICE_1)
                        .withAvailability(START_1, END_1)
                        .build(),
                hotelRoomOffer()
                        .withHotelId(HOTEL_ID_1)
                        .withHotelRoomNumber(HOTEL_ROOM_NUMBER_2)
                        .withPrice(PRICE_1)
                        .withAvailability(START_1, END_1)
                        .build(),
                new Object()
        );
    }

    private HotelRoomOffer givenHotelRoomOffer() {
        return hotelRoomOffer()
                .withHotelId(HOTEL_ID_1)
                .withHotelRoomNumber(HOTEL_ROOM_NUMBER_1)
                .withPrice(PRICE_1)
                .withAvailability(START_1, END_1)
                .build();
    }
}