package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferService {
    private final ApartmentOfferRepository apartmentOfferRepository;
    private final ApartmentRepository apartmentRepository;

    ApartmentOfferService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
        this.apartmentOfferRepository = apartmentOfferRepository;
        this.apartmentRepository = apartmentRepository;
    }

    void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
        if (apartmentRepository.existById(apartmentId)) {
            ApartmentOffer apartmentOffer = apartmentOffer()
                    .withApartmentId(apartmentId)
                    .withPrice(price)
                    .withAvailability(start, end)
                    .build();
            apartmentOfferRepository.save(apartmentOffer);
        } else {
            throw new ApartmentNotFoundException("Apartment with id: " + apartmentId + " does not exist.");
        }
    }
}
