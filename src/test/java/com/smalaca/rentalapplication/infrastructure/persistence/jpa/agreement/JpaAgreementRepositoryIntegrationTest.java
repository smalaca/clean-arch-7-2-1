package com.smalaca.rentalapplication.infrastructure.persistence.jpa.agreement;

import com.smalaca.rentalapplication.domain.agreement.Agreement;
import com.smalaca.rentalapplication.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.agreement.Agreement.Builder.agreement;
import static com.smalaca.rentalapplication.domain.booking.RentalType.APARTMENT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class JpaAgreementRepositoryIntegrationTest {
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID = "5678";
    private static final String OWNER_ID = "1982";
    private static final Money PRICE = Money.of(BigDecimal.valueOf(42));
    public static final LocalDate TODAY = LocalDate.now();
    private static final List<LocalDate> DAYS = asList(TODAY, TODAY.plusDays(1));

    private final JpaAgreementRepository repository = new JpaAgreementRepository();

    @Test
    void shouldSaveAgreement() {
        Agreement agreement = agreement()
                .withRentalType(APARTMENT)
                .withRentalPlaceId(RENTAL_PLACE_ID)
                .withOwnerId(OWNER_ID)
                .withTenantId(TENANT_ID)
                .withDays(DAYS)
                .withPrice(PRICE)
                .build();

        repository.save(agreement);
    }

    @Test
    void shouldFindNothing() {
        Agreement actual = repository.findById(UUID.randomUUID());

        assertThat(actual).isNull();
    }
}