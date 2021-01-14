package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaHotelRepository implements HotelRepository {
    private final SpringJpaHotelRepository hotelRepository;

    JpaHotelRepository(SpringJpaHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public String save(Hotel hotel) {
        return hotelRepository.save(hotel).id();
    }

    @Override
    public Hotel findById(String hotelId) {
        return hotelRepository.findById(UUID.fromString(hotelId)).get();
    }
}
