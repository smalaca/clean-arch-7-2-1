package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferDomainService;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelRoomOfferApplicationServiceFactory {
    @Bean
    HotelRoomOfferApplicationService hotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository) {
        return new HotelRoomOfferApplicationService(hotelRoomOfferRepository, hotelRepository, new HotelRoomOfferDomainService());
    }
}
