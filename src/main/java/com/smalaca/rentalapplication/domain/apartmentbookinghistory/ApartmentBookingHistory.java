package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ApartmentBookingHistory {
    @Id
    private final String apartmentId;
    @OneToMany
    private final List<ApartmentBooking> bookings = new ArrayList<>();

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }
}
