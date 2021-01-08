package com.smalaca.rentalapplication.infrastructure.eventchannel.spring;

import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomBooked;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
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

    @Override
    public void publish(BookingAccepted bookingAccepted) {
        publisher.publishEvent(bookingAccepted);
    }
}
