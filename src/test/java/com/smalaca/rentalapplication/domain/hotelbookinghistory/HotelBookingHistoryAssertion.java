package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HotelBookingHistoryAssertion {
    private final HotelBookingHistory actual;

    private HotelBookingHistoryAssertion(HotelBookingHistory actual) {
        this.actual = actual;
    }

    public static HotelBookingHistoryAssertion assertThat(HotelBookingHistory actual) {
        return new HotelBookingHistoryAssertion(actual);
    }

    public HotelBookingHistoryAssertion hasHotelRoomBookingHistoryFor(String hotelRoomId, LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        hasHotelRoomBookingHistories().satisfies(actualBookings -> {
            Assertions.assertThat(asHotelRoomHistories(actualBookings)).anySatisfy(hotelRoomBookingHistory -> {
                HotelRoomBookingHistoryAssertion.assertThat(hotelRoomBookingHistory)
                        .hasHotelRoomIdEqualTo(hotelRoomId)
                        .hasHotelRoomBookingFor(bookingDateTime, tenantId, days);
            });
        });

        return this;
    }

    private AbstractObjectAssert<?, ?> hasHotelRoomBookingHistories() {
        return Assertions.assertThat(actual).extracting("hotelRoomBookingHistories");
    }

    private List<HotelRoomBookingHistory> asHotelRoomHistories(Object actualBookings) {
        return (List<HotelRoomBookingHistory>) actualBookings;
    }
}