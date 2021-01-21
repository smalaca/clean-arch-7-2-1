package com.smalaca.rentalapplication.infrastructure.persistence.jpa.aggrement;

import com.smalaca.rentalapplication.domain.aggrement.Agreement;
import com.smalaca.rentalapplication.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.smalaca.rentalapplication.domain.aggrement.Agreement.Builder.agreement;
import static com.smalaca.rentalapplication.domain.booking.RentalType.APARTMENT;
import static java.util.Arrays.asList;

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
}