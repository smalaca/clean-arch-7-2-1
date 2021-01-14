package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomNotFoundException;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

@Service
public class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRepository hotelRepository;

    HotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRepository = hotelRepository;
    }

    public UUID add(HotelRoomOfferDto dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId());

        if (hotel.hasRoomWithNumber(dto.getNumber())) {
            HotelRoomOffer hotelRoomOffer = hotelRoomOffer()
                    .withHotelRoomId(dto.getHotelRoomId())
                    .withPrice(dto.getPrice())
                    .withAvailability(dto.getStart(), dto.getEnd())
                    .build();

            return hotelRoomOfferRepository.save(hotelRoomOffer);
        } else {
            throw new HotelRoomNotFoundException(dto.getHotelRoomId());
        }
    }
}
