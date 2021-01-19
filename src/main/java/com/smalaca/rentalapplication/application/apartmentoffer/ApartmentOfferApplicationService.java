package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferFactory;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;

import java.util.UUID;

public class ApartmentOfferApplicationService {
    private final ApartmentOfferRepository apartmentOfferRepository;
    private final ApartmentOfferFactory apartmentOfferFactory;

    ApartmentOfferApplicationService(ApartmentOfferRepository apartmentOfferRepository, ApartmentOfferFactory apartmentOfferFactory) {
        this.apartmentOfferRepository = apartmentOfferRepository;
        this.apartmentOfferFactory = apartmentOfferFactory;
    }

    public UUID add(ApartmentOfferDto dto) {
        ApartmentOffer apartmentOffer = apartmentOfferFactory.create(dto.getApartmentId(), dto.getPrice(), dto.getStart(), dto.getEnd());

        return apartmentOfferRepository.save(apartmentOffer);
    }
}
