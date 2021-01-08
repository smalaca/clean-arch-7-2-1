package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentAvailability;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.Money;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;

import java.math.BigDecimal;
import java.time.LocalDate;

class ApartmentOfferService {
    private final ApartmentOfferRepository repository;

    ApartmentOfferService(ApartmentOfferRepository repository) {
        this.repository = repository;
    }

    void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
        ApartmentOffer apartmentOffer = new ApartmentOffer(apartmentId, new Money(price), new ApartmentAvailability(start, end));
        repository.save(apartmentOffer);
    }
}
