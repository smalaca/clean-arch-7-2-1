package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.Consumer;

public class ApartmentBookingHistoryAssertion {
    private final ApartmentBookingHistory actual;

    private ApartmentBookingHistoryAssertion(ApartmentBookingHistory actual) {
        this.actual = actual;
    }

    public static ApartmentBookingHistoryAssertion assertThat(ApartmentBookingHistory actual) {
        return new ApartmentBookingHistoryAssertion(actual);
    }

    ApartmentBookingHistoryAssertion isEqualTo(ApartmentBookingHistory expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
        return this;
    }

    public ApartmentBookingHistoryAssertion hasOneApartmentBooking() {
        return hasApartmentBookingsAmount(1);
    }

    public ApartmentBookingHistoryAssertion hasApartmentBookingsAmount(int expected) {
        hasApartmentBookings().satisfies(actualBookings -> {
            Assertions.assertThat(asApartmentBookings(actualBookings)).hasSize(expected);
        });

        return this;
    }

    ApartmentBookingHistoryAssertion hasApartmentBookingEqualTo(ApartmentBooking expected) {
        hasApartmentBookings().satisfies(actualBookings -> {
            Assertions.assertThat(asApartmentBookings(actualBookings)).anySatisfy(apartmentBooking -> {
                Assertions.assertThat(apartmentBooking).isEqualTo(expected);
            });
        });

        return this;

    }

    public ApartmentBookingHistoryAssertion hasApartmentBookingThatSatisfies(Consumer<ApartmentBooking> requirements) {
        hasApartmentBookings().satisfies(actualBookings -> {
            Assertions.assertThat(asApartmentBookings(actualBookings)).anySatisfy(requirements);
        });

        return this;
    }

    private AbstractObjectAssert<?, ?> hasApartmentBookings() {
        return Assertions.assertThat(actual).extracting("bookings");
    }

    private List<ApartmentBooking> asApartmentBookings(Object actualBookings) {
        return (List<ApartmentBooking>) actualBookings;
    }
}