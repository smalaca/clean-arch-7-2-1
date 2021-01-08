package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.time.LocalDate;

class ApartmentAvailability {
    private final LocalDate start;
    private final LocalDate end;

    ApartmentAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    static ApartmentAvailability of(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new ApartmentAvailabilityException();
        }
        return new ApartmentAvailability(start, end);
    }
}
