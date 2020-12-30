package com.smalaca.rentalapplication.domain.apartment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

    @ParameterizedTest
    @MethodSource("daysBetweenStartAndEnd")
    void shouldReturnAllDaysBetweenStartAndEnd(LocalDate start, LocalDate end, Iterable<LocalDate> expected) {
        List<LocalDate> actual = new Period(start, end).asDays();

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> daysBetweenStartAndEnd() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 3),
                        asList(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 3))),
                Arguments.of(
                        LocalDate.of(2020, 10, 1), LocalDate.of(2020, 10, 2),
                        asList(LocalDate.of(2020, 10, 1), LocalDate.of(2020, 10, 2))),
                Arguments.of(
                        LocalDate.of(2020, 5, 5), LocalDate.of(2020, 5, 10),
                        asList(
                                LocalDate.of(2020, 5, 5), LocalDate.of(2020, 5, 6), LocalDate.of(2020, 5, 7),
                                LocalDate.of(2020, 5, 8), LocalDate.of(2020, 5, 9), LocalDate.of(2020, 5, 10)))
        );
    }
}