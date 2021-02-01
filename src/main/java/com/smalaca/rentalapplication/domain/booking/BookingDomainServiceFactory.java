package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;

public class BookingDomainServiceFactory {
    public BookingDomainService create(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        BookingEventsPublisher bookingEventsPublisher = new BookingEventsPublisher(eventIdFactory, clock, eventChannel);
        return new BookingDomainService(bookingEventsPublisher);
    }
}
