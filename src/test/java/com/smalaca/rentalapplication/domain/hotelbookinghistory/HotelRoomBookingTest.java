package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

class HotelRoomBookingTest {
    private static final LocalDateTime BOOKING_DATE_TIME_1 = LocalDateTime.of(2020, 1, 2, 3, 4);
    private static final String TENANT_ID_1 = "tenantId1";
    private static final List<LocalDate> DAYS_1 = asList(LocalDate.of(2020, 5, 6), LocalDate.of(2020, 7, 8));
    private static final LocalDateTime BOOKING_DATE_TIME_2 = LocalDateTime.of(2020, 1, 3, 5, 7);
    private static final String TENANT_ID_2 = "1212";
    private static final List<LocalDate> DAYS_2 = asList(LocalDate.of(2020, 9, 10), LocalDate.of(2020, 11, 12));

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        HotelRoomBooking actual = givenHotelRoomBooking();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfHotelRoomBookingRepresentsTheSameAggregate() {
        HotelRoomBooking toCompare = givenHotelRoomBooking();

        HotelRoomBooking actual = givenHotelRoomBooking();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsHotelRoomBooking() {
        HotelRoomBooking actual = givenHotelRoomBooking();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTeSameHotelRoomBookings")
    void shouldRecognizeHotelRoomBookingsDoesNotRepresentTheSameAggregate(Object toCompare) {
        HotelRoomBooking actual = givenHotelRoomBooking();

        Assertions.assertThat(actual.equals(toCompare)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private HotelRoomBooking givenHotelRoomBooking() {
        return new HotelRoomBooking(BOOKING_DATE_TIME_1, TENANT_ID_1, DAYS_1);
    }

    private static Stream<Object> notTeSameHotelRoomBookings() {
        return Stream.of(
                new HotelRoomBooking(BOOKING_DATE_TIME_2, TENANT_ID_1, DAYS_1),
                new HotelRoomBooking(BOOKING_DATE_TIME_1, TENANT_ID_2, DAYS_1),
                new HotelRoomBooking(BOOKING_DATE_TIME_1, TENANT_ID_1, DAYS_2),
                new Object()
        );
    }
}