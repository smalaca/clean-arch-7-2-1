package com.smalaca.rentalapplication.domain.apartmentoffer;

public class ApartmentOfferNotFoundException extends RuntimeException {
    public ApartmentOfferNotFoundException(String apartmentId) {
        super("Offer for apartment with id: " + apartmentId + " does not exist.");
    }
}
