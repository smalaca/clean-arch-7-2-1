package com.smalaca.rentalapplication.application.hotelroom;

import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomEventsPublisher;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelRoomApplicationServiceFactory {
    @Bean
    HotelRoomApplicationService hotelRoomApplicationService(
            HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        HotelRoomEventsPublisher hotelRoomEventsPublisher = new HotelRoomEventsPublisher(eventIdFactory, clock, eventChannel);

        return new HotelRoomApplicationService(hotelRoomRepository, bookingRepository, hotelRoomEventsPublisher);
    }
}
