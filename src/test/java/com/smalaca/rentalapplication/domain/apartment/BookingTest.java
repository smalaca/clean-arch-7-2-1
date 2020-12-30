package com.smalaca.rentalapplication.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartment.BookingAssertion.assertThat;

class BookingTest {

    @Test
    void shouldCreateBookingForApartment() {
        String rentalPlaceId = "567";
        String tenantId = "789";
        Period period = new Period(LocalDate.of(2020, 3, 4), LocalDate.of(2020, 3, 6));

        Booking actual = Booking.apartment(rentalPlaceId, tenantId, period);

        assertThat(actual)
                .isOpen()
                .isApartment()
                .hasRentalPlaceIdEqualTo(rentalPlaceId)
                .hasTenantIdEqualTo(tenantId)
                .containsAllDays(
                        LocalDate.of(2020, 3, 4), LocalDate.of(2020, 3, 5), LocalDate.of(2020, 3, 6));
    }

    @Test
    void shouldCreateBookingForHotelRoom() {

    }
}