package com.smalaca.rentalapplication.domain.agreement;

import com.smalaca.rentalapplication.domain.booking.RentalType;
import com.smalaca.rentalapplication.domain.money.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

class AgreementTest {
    private static final RentalType RENTAL_TYPE_1 = RentalType.APARTMENT;
    private static final String RENTAL_PLACE_ID_1 = "5748";
    private static final String OWNER_ID_1 = "4346";
    private static final String TENANT_ID_1 = "1234";
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private static final Money PRICE_1 = Money.of(BigDecimal.valueOf(123.45));
    private static final List<LocalDate> DAYS_1 = asList(TODAY, TOMORROW);
    private static final RentalType RENTAL_TYPE_2 = RentalType.HOTEL_ROOM;
    private static final String RENTAL_PLACE_ID_2 = "1357";
    private static final String TENANT_ID_2 = "2468";
    private static final String OWNER_ID_2 = "989723";
    private static final Money PRICE_2 = Money.of(BigDecimal.valueOf(567.89));
    private static final List<LocalDate> DAYS_2 = asList(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        Agreement actual = givenAgreement();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfAgreementRepresentsTheSameAggregate() {
        Agreement toCompare = givenAgreement();

        Agreement actual = givenAgreement();

        Assertions.assertThat(actual.equals(toCompare)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(toCompare.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsAgreement() {
        Agreement actual = givenAgreement();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTeSameAgreements")
    void shouldRecognizeAgreementsDoesNotRepresentTheSameAggregate(Object toCompare) {
        Agreement actual = givenAgreement();

        Assertions.assertThat(actual.equals(toCompare)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(toCompare.hashCode());
    }

    private static Stream<Object> notTeSameAgreements() {
        return Stream.of(
                givenAgreementBuilder().withRentalType(RENTAL_TYPE_2).build(),
                givenAgreementBuilder().withRentalPlaceId(RENTAL_PLACE_ID_2).build(),
                givenAgreementBuilder().withOwnerId(OWNER_ID_2).build(),
                givenAgreementBuilder().withTenantId(TENANT_ID_2).build(),
                givenAgreementBuilder().withPrice(PRICE_2).build(),
                givenAgreementBuilder().withDays(DAYS_2).build(),
                new Object()
        );
    }

    private Agreement givenAgreement() {
        return givenAgreementBuilder().build();
    }

    private static Agreement.Builder givenAgreementBuilder() {
        return Agreement.Builder.agreement()
                .withRentalType(RENTAL_TYPE_1)
                .withRentalPlaceId(RENTAL_PLACE_ID_1)
                .withOwnerId(OWNER_ID_1)
                .withTenantId(TENANT_ID_1)
                .withPrice(PRICE_1)
                .withDays(DAYS_1);
    }
}