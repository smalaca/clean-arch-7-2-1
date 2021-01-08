package com.smalaca.rentalapplication.domain.hotelroom;

import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;

import java.time.LocalDate;
import java.util.List;

public class HotelRoomEventsPublisher {
    private final EventIdFactory eventIdFactory;
    private final Clock clock;
    private final EventChannel eventChannel;

    public HotelRoomEventsPublisher(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
        this.eventChannel = eventChannel;
    }

    void publishHotelRoomBooked(String hotelRoomId, String hotelId, String tenantId, List<LocalDate> days) {
        HotelRoomBooked hotelRoomBooked = new HotelRoomBooked(eventIdFactory.create(), clock.now(), hotelRoomId, hotelId, tenantId, days);
        eventChannel.publish(hotelRoomBooked);
    }
}
