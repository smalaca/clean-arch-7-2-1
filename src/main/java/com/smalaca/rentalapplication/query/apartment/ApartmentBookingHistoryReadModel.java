package com.smalaca.rentalapplication.query.apartment;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistoryReadModel {
    @Id
    private String apartmentId;

    @ElementCollection
    private List<ApartmentBookingReadModel> bookings = new ArrayList<>();

    private ApartmentBookingHistoryReadModel() {}

    public ApartmentBookingHistoryReadModel(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public List<ApartmentBookingReadModel> getBookings() {
        return bookings;
    }
}
