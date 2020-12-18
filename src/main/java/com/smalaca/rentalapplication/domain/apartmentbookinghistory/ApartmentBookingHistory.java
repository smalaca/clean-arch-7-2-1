package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import java.util.ArrayList;
import java.util.List;

public class ApartmentBookingHistory {
    private final String apartmentId;
    private final List<ApartmentBooking> bookings = new ArrayList<>();

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }
}
