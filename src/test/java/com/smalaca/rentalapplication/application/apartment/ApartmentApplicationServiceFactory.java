package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {
    @Bean
    ApartmentApplicationService apartmentApplicationService(ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
        ApartmentEventsPublisher apartmentEventsPublisher = new ApartmentEventsPublisher(new EventIdFactory(), eventChannel);

        return new ApartmentApplicationService(apartmentRepository, bookingRepository, apartmentEventsPublisher);
    }
}
