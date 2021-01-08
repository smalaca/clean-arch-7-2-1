package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.time.LocalDate;

class ApartmentAvailability {
    private final LocalDate start;
    private final LocalDate end;

    ApartmentAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
