package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistory {
    @Id
    private String apartmentId;

    @ElementCollection
    private List<ApartmentBooking> bookings = new ArrayList<>();

    private ApartmentBookingHistory() {}

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }
}
