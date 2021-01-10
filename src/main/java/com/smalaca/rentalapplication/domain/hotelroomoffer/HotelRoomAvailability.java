package com.smalaca.rentalapplication.domain.hotelroomoffer;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class HotelRoomAvailability {
    private LocalDate start;
    private LocalDate end;

    private HotelRoomAvailability() {}

    private HotelRoomAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    static HotelRoomAvailability fromStart(LocalDate start) {
        return from(start, start.plusYears(1));
    }

    static HotelRoomAvailability from(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw HotelRoomAvailabilityException.startAfterEnd(start, end);
        }

        if (start.isBefore(LocalDate.now())) {
            throw HotelRoomAvailabilityException.startFromPast(start);
        }

        return new HotelRoomAvailability(start, end);
    }
}
