package com.smalaca.rentalapplication.domain.period;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {
    private static final LocalDate START = LocalDate.of(2040, 10, 11);
    private static final LocalDate END = LocalDate.of(2041, 10, 11);

    @ParameterizedTest
    @MethodSource("daysBetweenStartAndEnd")
    void shouldReturnAllDaysBetweenStartAndEnd(LocalDate start, LocalDate end, Iterable<LocalDate> expected) {
        List<LocalDate> actual = Period.from(start, end).asDays();

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> daysBetweenStartAndEnd() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2040, 1, 1), LocalDate.of(2040, 1, 3),
                        asList(LocalDate.of(2040, 1, 1), LocalDate.of(2040, 1, 2), LocalDate.of(2040, 1, 3))),
                Arguments.of(
                        LocalDate.of(2040, 10, 1), LocalDate.of(2040, 10, 2),
                        asList(LocalDate.of(2040, 10, 1), LocalDate.of(2040, 10, 2))),
                Arguments.of(
                        LocalDate.of(2040, 5, 5), LocalDate.of(2040, 5, 10),
                        asList(
                                LocalDate.of(2040, 5, 5), LocalDate.of(2040, 5, 6), LocalDate.of(2040, 5, 7),
                                LocalDate.of(2040, 5, 8), LocalDate.of(2040, 5, 9), LocalDate.of(2040, 5, 10)))
        );
    }

    @Test
    void shouldReturnOneDateWhenStartAndEndAreTheSame() {
        LocalDate date = LocalDate.of(2040, 10, 11);

        List<LocalDate> actual = Period.from(date, date).asDays();

        assertThat(actual).containsExactly(date);
    }

    @Test
    void shouldCheckBeEqualWithItself() {
        Period actual = Period.from(START, END);

        assertThat(actual.equals(actual)).isTrue();
        assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldCheckBeEqualWithInstanceThatHaveTheSameValues() {
        Period expected = Period.from(START, END);

        Period actual = Period.from(START, END);

        assertThat(actual.equals(expected)).isTrue();
        assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
    }

    @ParameterizedTest
    @MethodSource("notEqualPeriods")
    void shouldNotBeEqualTo(Object expected) {
        Period actual = Period.from(START, END);

        assertThat(actual.equals(expected)).isFalse();
        assertThat(actual.hashCode()).isNotEqualTo(expected.hashCode());
    }

    private static Stream<Object> notEqualPeriods() {
        return Stream.of(Period.from(START, START.plusDays(1)), Period.from(LocalDate.now(), END), new Object());
    }

    @Test
    void shouldNotBeEqualToNull() {
        Period actual = Period.from(START, END);

        assertThat(actual.equals(null)).isFalse();
    }
}