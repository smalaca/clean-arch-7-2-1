package com.smalaca.rentalapplication.domain.period;

import java.time.LocalDate;

public class PeriodException extends RuntimeException {
    private PeriodException(String message) {
        super(message);
    }

    static RuntimeException startDateFromPast(LocalDate start) {
        return new PeriodException("Start date: " + start + " is past date.");
    }
}
