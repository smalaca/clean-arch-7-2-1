package com.smalaca.rentalapplication.domain.booking;

import java.util.List;

public class BookingDomainService {
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingDomainService(BookingEventsPublisher bookingEventsPublisher) {
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    void accept(Booking booking, List<Booking> bookings) {
        if (bookings.isEmpty()) {
            booking.accept(bookingEventsPublisher);
        } else {
            booking.reject();
        }
    }
}
