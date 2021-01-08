package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.time.LocalDate;

@SuppressWarnings("PMD.UnusedPrivateField")
class ApartmentAvailability {
    private final LocalDate start;
    private final LocalDate end;

    ApartmentAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    static ApartmentAvailability of(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new ApartmentAvailabilityException(start, end);
        }
        return new ApartmentAvailability(start, end);
    }
}
