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

    @SuppressWarnings("checkstyle:ParameterNumber")
    public void add(String name, String street, String postalCode, String buildingNumber, String city, String country) {
        Hotel hotel = new HotelFactory().create(name, street, postalCode, buildingNumber, city, country);

        hotelRepository.save(hotel);
    }
}
