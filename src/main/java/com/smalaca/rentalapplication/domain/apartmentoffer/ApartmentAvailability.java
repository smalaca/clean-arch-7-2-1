package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.time.LocalDate;

public class ApartmentAvailability {
    private final LocalDate start;
    private final LocalDate end;

    public ApartmentAvailability(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
