package com.smalaca.rentalapplication.domain.apartment;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;

import static java.util.Arrays.asList;

class BookingAssertion {
    private final Booking actual;

    private BookingAssertion(Booking actual) {
        this.actual = actual;
    }

    static BookingAssertion assertThat(Booking actual) {
        return new BookingAssertion(actual);
    }

    BookingAssertion isOpen() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", BookingStatus.OPEN);
        return this;
    }

    BookingAssertion isApartment() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", RentalType.APARTMENT);
        return this;
    }

    BookingAssertion hasRentalPlaceIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
        return this;
    }

    BookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    BookingAssertion containsAllDays(LocalDate... expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("days", asList(expected));
        return this;
    }
}
