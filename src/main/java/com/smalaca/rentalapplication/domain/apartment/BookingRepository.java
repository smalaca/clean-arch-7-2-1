package com.smalaca.rentalapplication.domain.apartment;

public interface BookingRepository {
    void save(Booking booking);

    Booking findById(String bookingId);
}
