package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotel.HotelEventsPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelApplicationServiceFactory {
    @Bean
    @SuppressWarnings("checkstyle:ParameterNumber")
    HotelApplicationService hotelApplicationService(
            HotelRepository hotelRepository, BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        HotelEventsPublisher hotelEventsPublisher = new HotelEventsPublisher(eventIdFactory, clock, eventChannel);

        return new HotelApplicationService(hotelRepository, bookingRepository, hotelEventsPublisher);
    }
}
