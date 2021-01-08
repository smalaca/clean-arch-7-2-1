package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferService {
    private final ApartmentOfferRepository repository;

    ApartmentOfferService(ApartmentOfferRepository repository) {
        this.repository = repository;
    }

    void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
        ApartmentOffer apartmentOffer = apartmentOffer()
            .withApartmentId(apartmentId)
            .withPrice(price)
            .withAvailability(start, end)
            .build();
        repository.save(apartmentOffer);
    }
}
