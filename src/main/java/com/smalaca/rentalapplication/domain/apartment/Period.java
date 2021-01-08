package com.smalaca.rentalapplication.domain.apartment;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@EqualsAndHashCode
public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    LocalDate getStart() {
        return start;
    }

    LocalDate getEnd() {
        return end;
    }

    public List<LocalDate> asDays() {
        List<LocalDate> dates = start.datesUntil(end).collect(toList());
        dates.add(end);

        return dates;
    }
}
