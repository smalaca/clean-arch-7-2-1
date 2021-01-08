package com.smalaca.rentalapplication.domain.apartmentoffer;

public class ApartmentAvailabilityException extends RuntimeException {
    ApartmentAvailabilityException() {
        super("Start date of availability is after end date.");
    }
}
