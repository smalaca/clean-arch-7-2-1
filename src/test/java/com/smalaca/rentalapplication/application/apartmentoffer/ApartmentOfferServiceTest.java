package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferAssertion;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentOfferServiceTest {
    private static final String APARTMENT_ID = "1234";
    private static final BigDecimal PRICE = BigDecimal.valueOf(123);
    private static final LocalDate START = LocalDate.of(2020, 10, 11);
    private static final LocalDate END = LocalDate.of(2020, 10, 20);

    private final ApartmentOfferRepository apartmentOfferRepository = mock(ApartmentOfferRepository.class);
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final ApartmentOfferService service = new ApartmentOfferService(apartmentOfferRepository, apartmentRepository);

    @Test
    void shouldCreateApartmentOfferForExistingApartment() {
        ArgumentCaptor<ApartmentOffer> captor = ArgumentCaptor.forClass(ApartmentOffer.class);
        givenExistingApartment();

        service.add(givenApartmentOfferDto());

        then(apartmentOfferRepository).should().save(captor.capture());
        ApartmentOfferAssertion.assertThat(captor.getValue())
                .hasApartmentIdEqualTo(APARTMENT_ID)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }

    @Test
    void shouldRecognizeApartmentDoesNotExist() {
        givenNonExistingApartment();

        ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> service.add(givenApartmentOfferDto()));

        assertThat(actual).hasMessage("Apartment with id: " + APARTMENT_ID + " does not exist.");
    }

    private ApartmentOfferDto givenApartmentOfferDto() {
        return new ApartmentOfferDto(APARTMENT_ID, PRICE, START, END);
    }

    private void givenNonExistingApartment() {
        given(apartmentRepository.existById(APARTMENT_ID)).willReturn(false);
    }

    private void givenExistingApartment() {
        given(apartmentRepository.existById(APARTMENT_ID)).willReturn(true);
    }
}
