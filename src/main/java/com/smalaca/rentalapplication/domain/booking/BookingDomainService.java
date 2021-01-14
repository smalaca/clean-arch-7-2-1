package com.smalaca.rentalapplication.domain.booking;

import java.util.List;

public class BookingDomainService {
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingDomainService(BookingEventsPublisher bookingEventsPublisher) {
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    void accept(Booking booking, List<Booking> bookings) {
        if (hasNoCollisions(booking, bookings)) {
            booking.accept(bookingEventsPublisher);
        } else {
            booking.reject(bookingEventsPublisher);
        }
    }

    private boolean hasNoCollisions(Booking bookingToAccept, List<Booking> bookings) {
        return bookings.stream().noneMatch(booking -> booking.hasCollisionWith(bookingToAccept));
    }
}
