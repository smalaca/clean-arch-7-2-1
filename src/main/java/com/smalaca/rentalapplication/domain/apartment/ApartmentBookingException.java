package com.smalaca.rentalapplication.domain.apartment;

public class ApartmentBookingException extends RuntimeException {
    ApartmentBookingException() {
        super("There are accepted bookings in given period.");
    }
}
