package com.smalaca.rentalapplication.application.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingDomainService;
import com.smalaca.rentalapplication.domain.booking.BookingEventsPublisher;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import org.springframework.context.event.EventListener;

public class BookingCommandHandler {
    private final BookingRepository bookingRepository;
    private final BookingDomainService bookingDomainService;
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingCommandHandler(BookingRepository bookingRepository, BookingDomainService bookingDomainService, BookingEventsPublisher bookingEventsPublisher) {
        this.bookingRepository = bookingRepository;
        this.bookingDomainService = bookingDomainService;
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    @EventListener
    public void reject(BookingReject bookingReject) {
        Booking booking = bookingRepository.findById(bookingReject.getBookingId());

        booking.reject();

        bookingRepository.save(booking);
    }

    @EventListener
    public void accept(BookingAccept bookingAccept) {
        Booking booking = bookingRepository.findById(bookingAccept.getId());

        booking.accept(bookingEventsPublisher);

        bookingRepository.save(booking);
    }
}
