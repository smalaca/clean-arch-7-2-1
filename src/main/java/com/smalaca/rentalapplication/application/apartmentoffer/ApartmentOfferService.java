package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;

import static com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferService {
    private final ApartmentOfferRepository apartmentOfferRepository;
    private final ApartmentRepository apartmentRepository;

    ApartmentOfferService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
        this.apartmentOfferRepository = apartmentOfferRepository;
        this.apartmentRepository = apartmentRepository;
    }

    void add(ApartmentOfferDto dto) {
        if (apartmentRepository.existById(dto.getApartmentId())) {
            ApartmentOffer apartmentOffer = apartmentOffer()
                    .withApartmentId(dto.getApartmentId())
                    .withPrice(dto.getPrice())
                    .withAvailability(dto.getStart(), dto.getEnd())
                    .build();

            apartmentOfferRepository.save(apartmentOffer);
        } else {
            throw new ApartmentNotFoundException(dto.getApartmentId());
        }
    }
}
