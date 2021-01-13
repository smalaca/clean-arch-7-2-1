package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApartmentBookingAssertion {
    private final ApartmentBooking actual;

    private ApartmentBookingAssertion(ApartmentBooking actual) {
        this.actual = actual;
    }

    public static ApartmentBookingAssertion assertThat(ApartmentBooking actual) {
        return new ApartmentBookingAssertion(actual);
    }

    ApartmentBookingAssertion hasBookingDateTimeEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingDateTime", expected);
        return this;
    }

    public ApartmentBookingAssertion hasOwnerIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", expected);
        return this;
    }

    public ApartmentBookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    public ApartmentBookingAssertion hasPeriodThatHas(LocalDate expectedStart, LocalDate expectedEnd) {
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("period.periodStart", expectedStart)
                .hasFieldOrPropertyWithValue("period.periodEnd", expectedEnd);
        return this;
    }

    public ApartmentBookingAssertion isStart() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStep", BookingStep.START);
        return this;
    }
}
