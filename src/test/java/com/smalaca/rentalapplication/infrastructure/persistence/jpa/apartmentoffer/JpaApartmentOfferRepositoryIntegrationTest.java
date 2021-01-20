package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferAssertion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaApartmentOfferRepositoryIntegrationTest {
    private static final String APARTMENT_ID = "1234";
    private static final BigDecimal PRICE = BigDecimal.valueOf(123.45);
    private static final LocalDate START = LocalDate.of(2040, 10, 11);
    private static final LocalDate END = LocalDate.of(2040, 10, 20);

    @Autowired private JpaApartmentOfferRepository jpaApartmentOfferRepository;
    @Autowired private SpringJpaApartmentOfferRepository springJpaApartmentOfferRepository;

    private UUID apartmentOfferId;

    @AfterEach
    void deleteApartmentOffer() {
        springJpaApartmentOfferRepository.deleteById(apartmentOfferId);
    }

    @Test
    void shouldSaveApartmentOffer() {
        ApartmentOffer apartmentOffer = apartmentOffer()
                .withApartmentId(APARTMENT_ID)
                .withPrice(PRICE)
                .withAvailability(START, END)
                .build();

        apartmentOfferId = jpaApartmentOfferRepository.save(apartmentOffer);

        ApartmentOfferAssertion.assertThat(springJpaApartmentOfferRepository.findById(apartmentOfferId).get())
                .hasIdEqualTo(apartmentOfferId)
                .hasApartmentIdEqualTo(APARTMENT_ID)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }
}