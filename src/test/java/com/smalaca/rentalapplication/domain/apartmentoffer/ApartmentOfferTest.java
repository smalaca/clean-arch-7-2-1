package com.smalaca.rentalapplication.domain.apartmentoffer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferTest {
    private static final String APARTMENT_ID_1 = "1234";
    private static final BigDecimal PRICE_1 = BigDecimal.valueOf(123.45);
    private static final LocalDate START_1 = LocalDate.of(2040, 10, 11);
    private static final LocalDate END_1 = LocalDate.of(2040, 10, 20);
    private static final String APARTMENT_ID_2 = "3453";
    private static final BigDecimal PRICE_2 = BigDecimal.valueOf(678.91);
    private static final LocalDate START_2 = LocalDate.of(2040, 10, 30);
    private static final LocalDate END_2 = LocalDate.of(2040, 11, 21);

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        ApartmentOffer actual = givenApartmentOffer();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfApartmentOfferRepresentsTheSameAggregate() {
        ApartmentOffer toCompare = givenApartmentOffer();

        ApartmentOffer actual = givenApartmentOffer();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfApartmentOfferRepresentsTheSameAggregateWhenOnlyApartmentIdIsTheSame() {
        ApartmentOffer toCompare = apartmentOffer()
                .withApartmentId(APARTMENT_ID_1)
                .withPrice(PRICE_2)
                .withAvailability(START_2, END_2)
                .build();

        ApartmentOffer actual = givenApartmentOffer();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsApartmentOffer() {
        ApartmentOffer actual = givenApartmentOffer();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTeSameApartmentOffers")
    void shouldRecognizeApartmentOffersDoesNotRepresentTheSameAggregate(Object toCompare) {
        ApartmentOffer actual = givenApartmentOffer();

        Assertions.assertThat(actual.equals(toCompare)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private static Stream<Object> notTeSameApartmentOffers() {
        return Stream.of(
                apartmentOffer()
                        .withApartmentId(APARTMENT_ID_2)
                        .withPrice(PRICE_1)
                        .withAvailability(START_1, END_1)
                        .build(),
                new Object()
        );
    }

    private ApartmentOffer givenApartmentOffer() {
        return apartmentOffer()
                .withApartmentId(APARTMENT_ID_1)
                .withPrice(PRICE_1)
                .withAvailability(START_1, END_1)
                .build();
    }
}