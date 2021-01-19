package com.smalaca.rentalapplication.domain.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

public class ApartmentOfferFactory {
    private final ApartmentRepository apartmentRepository;

    public ApartmentOfferFactory(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public ApartmentOffer create(String apartmentId, BigDecimal price, LocalDate availabilityStart, LocalDate availabilityEnd) {
        if (apartmentRepository.existById(apartmentId)) {
            return apartmentOffer()
                    .withApartmentId(apartmentId)
                    .withPrice(price)
                    .withAvailability(availabilityStart, availabilityEnd)
                    .build();

        } else {
            throw new ApartmentNotFoundException(apartmentId);
        }
    }
}
