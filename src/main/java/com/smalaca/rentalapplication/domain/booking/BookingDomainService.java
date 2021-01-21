package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.agreement.Agreement;

import java.util.List;
import java.util.Optional;

public class BookingDomainService {
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingDomainService(BookingEventsPublisher bookingEventsPublisher) {
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    public Optional<Agreement> accept(Booking booking, List<Booking> bookings) {
        if (hasNoCollisions(booking, bookings)) {
            Agreement agreement = booking.accept(bookingEventsPublisher);
            return Optional.of(agreement);
        } else {
            booking.reject(bookingEventsPublisher);
        }
        return Optional.empty();
    }

    private boolean hasNoCollisions(Booking bookingToAccept, List<Booking> bookings) {
        return bookings.stream().noneMatch(booking -> booking.hasCollisionWith(bookingToAccept));
    }
}
