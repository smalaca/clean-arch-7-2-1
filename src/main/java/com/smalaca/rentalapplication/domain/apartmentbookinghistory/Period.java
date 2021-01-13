package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class Period {
    private LocalDate periodStart;
    private LocalDate periodEnd;

    private Period() {}

    public Period(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    private LocalDate getPeriodStart() {
        return periodStart;
    }

    private void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    private LocalDate getPeriodEnd() {
        return periodEnd;
    }

    private void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }
}
