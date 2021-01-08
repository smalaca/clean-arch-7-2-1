package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomNotFoundException;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRoomRepository hotelRoomRepository;

    HotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRoomRepository = hotelRoomRepository;
    }

    void add(HotelRoomOfferDto dto) {
        if (hotelRoomRepository.existById(dto.getHotelRoomId())) {
            HotelRoomOffer hotelRoomOffer = hotelRoomOffer()
                    .withHotelRoomId(dto.getHotelRoomId())
                    .withPrice(dto.getPrice())
                    .withAvailability(dto.getStart(), dto.getEnd())
                    .build();

            hotelRoomOfferRepository.save(hotelRoomOffer);
        } else {
            throw new HotelRoomNotFoundException(dto.getHotelRoomId());
        }
    }
}
