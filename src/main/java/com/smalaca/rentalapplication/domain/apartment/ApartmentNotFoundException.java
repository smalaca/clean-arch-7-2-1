package com.smalaca.rentalapplication.domain.apartment;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(String apartmentId) {
        super("Apartment with id: " + apartmentId + " does not exist.");
    }
}
