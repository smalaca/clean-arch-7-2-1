package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.aggrement.Aggrement;

import java.util.List;
import java.util.Optional;

public class BookingDomainService {
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingDomainService(BookingEventsPublisher bookingEventsPublisher) {
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    public Optional<Aggrement> accept(Booking booking, List<Booking> bookings) {
        if (hasNoCollisions(booking, bookings)) {
            booking.accept(bookingEventsPublisher);
        } else {
            booking.reject(bookingEventsPublisher);
        }
        return Optional.empty();
    }

    private boolean hasNoCollisions(Booking bookingToAccept, List<Booking> bookings) {
        return bookings.stream().noneMatch(booking -> booking.hasCollisionWith(bookingToAccept));
    }
}
