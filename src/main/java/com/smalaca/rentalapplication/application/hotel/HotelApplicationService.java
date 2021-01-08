package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelFactory;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelApplicationService {
    private final HotelRepository hotelRepository;

    public HotelApplicationService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void add(HotelDto hotelDto) {
        Hotel hotel = new HotelFactory().create(
                hotelDto.getName(), hotelDto.getStreet(), hotelDto.getPostalCode(), hotelDto.getBuildingNumber(),
                hotelDto.getCity(), hotelDto.getCountry());

        hotelRepository.save(hotel);
    }
}
