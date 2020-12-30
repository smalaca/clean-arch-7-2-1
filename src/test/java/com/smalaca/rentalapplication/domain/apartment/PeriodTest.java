package com.smalaca.rentalapplication.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

    @Test
    void shouldReturnAllDaysBetweenStartAndEnd() {
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2020, 1, 3);
        Period period = new Period(start, end);

        List<LocalDate> actual = period.asDays();

        assertThat(actual).containsExactly(start, LocalDate.of(2020, 1, 2), end);
    }
}