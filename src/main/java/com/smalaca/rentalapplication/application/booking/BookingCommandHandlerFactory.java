package com.smalaca.rentalapplication.application.booking;

import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookingCommandHandlerFactory {
    @Bean
    BookingCommandHandler bookingCommandHandler(BookingRepository bookingRepository, EventChannel eventChannel) {
        return new BookingCommandHandler(bookingRepository, eventChannel);
    }
}
