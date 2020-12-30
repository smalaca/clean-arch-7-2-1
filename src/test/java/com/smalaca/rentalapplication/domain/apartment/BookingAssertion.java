package com.smalaca.rentalapplication.domain.apartment;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

public class BookingAssertion {
    private final Booking actual;

    private BookingAssertion(Booking actual) {
        this.actual = actual;
    }

    public static BookingAssertion assertThat(Booking actual) {
        return new BookingAssertion(actual);
    }

    BookingAssertion isOpen() {
        return hasBookingStatusEqualTo(BookingStatus.OPEN);
    }

    BookingAssertion isAccepted() {
        return hasBookingStatusEqualTo(BookingStatus.ACCEPTED);
    }

    private BookingAssertion hasBookingStatusEqualTo(BookingStatus expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", expected);
        return this;
    }

    BookingAssertion isApartment() {
        return hasRentalTypeEqualTo(RentalType.APARTMENT);
    }

    public BookingAssertion isHotelRoom() {
        return hasRentalTypeEqualTo(RentalType.HOTEL_ROOM);
    }

    private BookingAssertion hasRentalTypeEqualTo(RentalType rentalType) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", rentalType);
        return this;
    }

    BookingAssertion hasRentalPlaceIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
        return this;
    }

    public BookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    BookingAssertion containsAllDays(LocalDate... expected) {
        return containsAllDays(asList(expected));
    }

    public BookingAssertion containsAllDays(List<LocalDate> expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("days", expected);
        return this;
    }
}
