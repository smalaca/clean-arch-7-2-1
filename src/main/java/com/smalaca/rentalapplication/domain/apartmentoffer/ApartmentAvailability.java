package com.smalaca.rentalapplication.domain.apartmentoffer;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class ApartmentAvailability {
    private LocalDate start;
    private LocalDate end;

    private ApartmentAvailability() {}

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
