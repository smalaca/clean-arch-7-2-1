package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {
    @Bean
    ApartmentApplicationService create(ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
        return new ApartmentApplicationService(apartmentRepository, eventChannel, bookingRepository);
    }
}
