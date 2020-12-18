package com.smalaca.rentalapplication.infrastructure.eventchannel.spring;

import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomBooked;
import org.springframework.context.ApplicationEventPublisher;

class SpringEventChannel implements EventChannel {
    private final ApplicationEventPublisher publisher;

    SpringEventChannel(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(ApartmentBooked apartmentBooked) {
        publisher.publishEvent(apartmentBooked);
    }

    @Override
    public void publish(HotelRoomBooked hotelRoomBooked) {
        publisher.publishEvent(hotelRoomBooked);
    }
}
