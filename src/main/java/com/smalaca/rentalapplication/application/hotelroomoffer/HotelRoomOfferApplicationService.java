package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository repository;

    HotelRoomOfferApplicationService(HotelRoomOfferRepository repository, HotelRoomRepository hotelRoomRepository) {
        this.repository = repository;
    }

    void add(HotelRoomOfferDto dto) {
        HotelRoomOffer hotelRoomOffer = hotelRoomOffer()
                .withHotelRoomId(dto.getHotelRoomId())
                .withPrice(dto.getPrice())
                .withAvailability(dto.getStart(), dto.getEnd())
                .build();

        repository.save(hotelRoomOffer);
    }
}
