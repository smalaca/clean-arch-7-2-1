package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.period.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

class ApartmentBookingHistoryTest {
    private static final String APARTMENT_ID = "1234";
    private static final String DIFFERENT_APARTMENT_ID = "4321";
    private static final String OWNER_ID = "5678";
    private static final LocalDateTime BOOKING_DATE_TIME_1 = LocalDateTime.of(2020, 2, 4, 6, 8);
    private static final String TENANT_ID_1 = "8989";
    private static final LocalDate START_1 = LocalDate.of(2020, 10, 11);
    private static final LocalDate END_1 = LocalDate.of(2020, 10, 12);
    private static final Period PERIOD_1 = new Period(START_1, END_1);
    private static final LocalDateTime BOOKING_DATE_TIME_2 = LocalDateTime.of(2020, 1, 3, 5, 7);
    private static final String TENANT_ID_2 = "1212";
    private static final LocalDate START_2 = LocalDate.of(2020, 1, 2);
    private static final LocalDate END_2 = LocalDate.of(2020, 3, 4);
    private static final Period PERIOD_2 = new Period(START_2, END_2);
    private static final LocalDateTime BOOKING_DATE_TIME_3 = LocalDateTime.of(2020, 1, 2, 3, 4);

    @Test
    void shouldAddFirstApartmentBookingIntoHistory() {
        ApartmentBookingHistory actual = new ApartmentBookingHistory(APARTMENT_ID);
        actual.addBookingStart(BOOKING_DATE_TIME_1, OWNER_ID, TENANT_ID_1, PERIOD_1);

        ApartmentBookingHistoryAssertion.assertThat(actual)
                .isEqualTo(new ApartmentBookingHistory(APARTMENT_ID))
                .hasOneApartmentBooking()
                .hasApartmentBookingEqualTo(ApartmentBooking.start(BOOKING_DATE_TIME_1, OWNER_ID, TENANT_ID_1, PERIOD_1));
    }

    @Test
    void shouldAddNextApartmentBookingIntoHistory() {
        ApartmentBookingHistory actual = new ApartmentBookingHistory(APARTMENT_ID);

        actual.addBookingStart(BOOKING_DATE_TIME_1, OWNER_ID, TENANT_ID_1, PERIOD_1);
        actual.addBookingStart(BOOKING_DATE_TIME_2, OWNER_ID, TENANT_ID_2, PERIOD_2);

        ApartmentBookingHistoryAssertion.assertThat(actual)
                .isEqualTo(new ApartmentBookingHistory(APARTMENT_ID))
                .hasApartmentBookingsAmount(2)
                .hasApartmentBookingEqualTo(ApartmentBooking.start(BOOKING_DATE_TIME_1, OWNER_ID, TENANT_ID_1, PERIOD_1))
                .hasApartmentBookingEqualTo(ApartmentBooking.start(BOOKING_DATE_TIME_2, OWNER_ID, TENANT_ID_2, PERIOD_2));
    }

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        ApartmentBookingHistory actual = givenApartmentBookingHistory();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfApartmentBookingHistoryRepresentsTheSameAggregate() {
        ApartmentBookingHistory toCompare = givenApartmentBookingHistory();

        ApartmentBookingHistory actual = givenApartmentBookingHistory();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfApartmentBookingHistoryRepresentsTheSameAggregateEvenWithDifferentApartmentBooking() {
        ApartmentBookingHistory toCompare = givenApartmentBookingHistory();
        toCompare.addBookingStart(BOOKING_DATE_TIME_3, OWNER_ID, TENANT_ID_2, new Period(START_1, END_2));

        ApartmentBookingHistory actual = givenApartmentBookingHistory();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsApartmentBookingHistory() {
        ApartmentBookingHistory actual = givenApartmentBookingHistory();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTeSameApartmentBookingHistories")
    void shouldRecognizeApartmentBookingHistoriesDoesNotRepresentTheSameAggregate(Object toCompare) {
        ApartmentBookingHistory actual = givenApartmentBookingHistory();

        Assertions.assertThat(actual.equals(toCompare)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private ApartmentBookingHistory givenApartmentBookingHistory() {
        ApartmentBookingHistory apartmentBookingHistory = new ApartmentBookingHistory(APARTMENT_ID);
        apartmentBookingHistory.addBookingStart(BOOKING_DATE_TIME_1, OWNER_ID, TENANT_ID_1, new Period(START_1, END_1));
        apartmentBookingHistory.addBookingStart(BOOKING_DATE_TIME_2, OWNER_ID, TENANT_ID_2, PERIOD_2);

        return apartmentBookingHistory;
    }

    private static Stream<Object> notTeSameApartmentBookingHistories() {
        return Stream.of(
                new ApartmentBookingHistory(DIFFERENT_APARTMENT_ID),
                new Object()
        );
    }
}