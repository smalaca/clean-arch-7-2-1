package com.smalaca.rentalapplication.application.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingDomainService;
import com.smalaca.rentalapplication.domain.booking.BookingEventsPublisher;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import org.springframework.context.event.EventListener;

import java.util.List;

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

        booking.reject(bookingEventsPublisher);

        bookingRepository.save(booking);
    }

    @EventListener
    public void accept(BookingAccept bookingAccept) {
        Booking booking = bookingRepository.findById(bookingAccept.getId());
        List<Booking> bookings = bookingRepository.findAllBy(booking.rentalPlaceIdentifier());

        bookingDomainService.accept(booking, bookings);

        bookingRepository.save(booking);
    }
}
