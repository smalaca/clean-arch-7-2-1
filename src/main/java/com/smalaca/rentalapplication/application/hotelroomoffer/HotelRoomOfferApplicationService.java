package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferDomainService;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;

import java.util.UUID;

public class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomOfferDomainService hotelRoomOfferDomainService;

    HotelRoomOfferApplicationService(
            HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository, HotelRoomOfferDomainService hotelRoomOfferDomainService) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomOfferDomainService = hotelRoomOfferDomainService;
    }

    public UUID add(HotelRoomOfferDto dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId());

        HotelRoomOffer hotelRoomOffer = hotelRoomOfferDomainService.createOfferForHotelRoom(hotel, dto.asDto());

        return hotelRoomOfferRepository.save(hotelRoomOffer);
    }
}
