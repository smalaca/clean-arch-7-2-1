package com.smalaca.rentalapplication.domain.booking;

public interface BookingRepository {
    String save(Booking booking);

    Booking findById(String bookingId);
}
