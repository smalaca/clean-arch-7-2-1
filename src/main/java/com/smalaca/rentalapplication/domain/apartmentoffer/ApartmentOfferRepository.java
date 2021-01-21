package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.util.UUID;

public interface ApartmentOfferRepository {
    UUID save(ApartmentOffer apartmentOffer);

    boolean existByApartmentId(String apartmentId);

    ApartmentOffer findByApartmentId(String apartmentId);
}
