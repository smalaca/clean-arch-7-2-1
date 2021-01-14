package com.smalaca.rentalapplication.domain.booking;

import java.util.List;

public interface BookingRepository {
    String save(Booking booking);

    Booking findById(String bookingId);

    List<Booking> findAllBy(RentalPlaceIdentifier identifier);
}
