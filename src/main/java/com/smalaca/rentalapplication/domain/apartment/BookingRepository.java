package com.smalaca.rentalapplication.domain.apartment;

public interface BookingRepository {
    String save(Booking booking);

    Booking findById(String bookingId);
}
