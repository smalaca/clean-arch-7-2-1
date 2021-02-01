package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.period.Period;

public class ApartmentEventsPublisher {
    private final EventIdFactory eventIdFactory;
    private final EventChannel eventChannel;
    private final Clock clock;

    public ApartmentEventsPublisher(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.eventChannel = eventChannel;
        this.clock = clock;
    }

    void publishApartmentBooked(String apartmentId, String ownerId, String tenantId, Period period) {
        ApartmentBooked apartmentBooked = new ApartmentBooked(eventIdFactory.create(), clock.now(), apartmentId, ownerId, tenantId, period);
        eventChannel.publish(apartmentBooked);
    }
}
