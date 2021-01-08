package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoom;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaHotelRoomRepository implements HotelRoomRepository {
    private final SpringJpaHotelRoomRepository hotelRoomRepository;

    JpaHotelRoomRepository(SpringJpaHotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    @Override
    public String save(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom).id();
    }

    @Override
    public HotelRoom findById(String id) {
        return hotelRoomRepository.findById(UUID.fromString(id)).orElseThrow(() -> new HotelRoomDoesNotExistException(id));
    }

    @Override
    public boolean existById(String hotelRoomId) {
        return false;
    }
}
