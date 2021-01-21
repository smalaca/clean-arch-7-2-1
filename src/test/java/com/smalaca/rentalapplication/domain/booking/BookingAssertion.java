package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
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

    public BookingAssertion isOpen() {
        return hasBookingStatusEqualTo(BookingStatus.OPEN);
    }

    public BookingAssertion isAccepted() {
        return hasBookingStatusEqualTo(BookingStatus.ACCEPTED);
    }

    public BookingAssertion isRejected() {
        return hasBookingStatusEqualTo(BookingStatus.REJECTED);
    }

    public BookingAssertion hasIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("id", expected);
        return this;
    }

    private BookingAssertion hasBookingStatusEqualTo(BookingStatus expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", expected);
        return this;
    }

    public BookingAssertion isEqualToBookingApartment(String rentalPlaceId, String tenantId, Period period) {
        Assertions.assertThat(actual).isEqualTo(Booking.apartment(rentalPlaceId, tenantId, period));
        return this;
    }

    public BookingAssertion isEqualToBookingApartment(String rentalPlaceId, String tenantId, String ownerId, Money price, Period period) {
        Assertions.assertThat(actual).isEqualTo(Booking.apartment(rentalPlaceId, tenantId, ownerId, price, period));
        return this;
    }

    public BookingAssertion isEqualToBookingHotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
        Assertions.assertThat(actual).isEqualTo(Booking.hotelRoom(rentalPlaceId, tenantId, days));
        return this;
    }

    BookingAssertion containsAllDays(LocalDate... expected) {
        return containsAllDays(asList(expected));
    }

    public BookingAssertion containsAllDays(List<LocalDate> expected) {
        Assertions.assertThat(actual).extracting("days").satisfies(days -> {
            List<LocalDate> actualDays = (List<LocalDate>) days;
            Assertions.assertThat(actualDays).containsExactlyElementsOf(expected);
        });
        return this;
    }
}
