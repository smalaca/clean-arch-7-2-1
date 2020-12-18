package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class BookingPeriod {
    private final LocalDate periodStart;
    private final LocalDate periodEnd;

    public BookingPeriod(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }
}
