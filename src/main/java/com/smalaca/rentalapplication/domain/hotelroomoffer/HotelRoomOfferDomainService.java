package com.smalaca.rentalapplication.domain.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomNotFoundException;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

public class HotelRoomOfferDomainService {
    public HotelRoomOffer createOfferForHotelRoom(Hotel hotel, CreateHotelRoomOffer createHotelRoomOffer) {
        if (hotel.hasRoomWithNumber(createHotelRoomOffer.getNumber())) {
            return hotelRoomOffer()
                    .withHotelRoomId(createHotelRoomOffer.getHotelRoomId())
                    .withPrice(createHotelRoomOffer.getPrice())
                    .withAvailability(createHotelRoomOffer.getStart(), createHotelRoomOffer.getEnd())
                    .build();
        } else {
            throw new HotelRoomNotFoundException(createHotelRoomOffer.getHotelRoomId());
        }
    }
}
