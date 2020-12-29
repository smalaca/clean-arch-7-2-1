package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoom;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;

class JpaHotelRoomRepository implements HotelRoomRepository {
    private final SpringJpaHotelRoomRepository hotelRoomRepository;

    JpaHotelRoomRepository(SpringJpaHotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    @Override
    public void save(HotelRoom hotelRoom) {

    }

    @Override
    public HotelRoom findById(String id) {
        return null;
    }
}
