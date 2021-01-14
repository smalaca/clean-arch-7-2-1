package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Service;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;

@Service
public class HotelApplicationService {
    private final HotelRepository hotelRepository;

    public HotelApplicationService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String add(HotelDto hotelDto) {
        Hotel hotel = hotel()
                .withName(hotelDto.getName())
                .withStreet(hotelDto.getStreet())
                .withPostalCode(hotelDto.getPostalCode())
                .withBuildingNumber(hotelDto.getBuildingNumber())
                .withCity(hotelDto.getCity())
                .withCountry(hotelDto.getCountry())
                .build();

        return hotelRepository.save(hotel);
    }
}
