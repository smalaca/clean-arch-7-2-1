package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.time.LocalDate;

class HotelRoomAvailability {
    private final LocalDate start;
    private final LocalDate end;

    private HotelRoomAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    static HotelRoomAvailability of(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new HotelRoomAvailabilityException(start, end);
        }

        if (start.isBefore(LocalDate.now())) {
            throw new HotelRoomAvailabilityException(start);
        }

        return new HotelRoomAvailability(start, end);
    }
}
