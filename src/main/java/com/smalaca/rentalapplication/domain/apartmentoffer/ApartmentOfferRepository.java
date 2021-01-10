package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.util.UUID;

public interface ApartmentOfferRepository {
    UUID save(ApartmentOffer apartmentOffer);
}
