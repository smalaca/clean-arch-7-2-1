package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.time.LocalDate;

class HotelRoomAvailability {
    private final LocalDate start;
    private final LocalDate end;

    HotelRoomAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
