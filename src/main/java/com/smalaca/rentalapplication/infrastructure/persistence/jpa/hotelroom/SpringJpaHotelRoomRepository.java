package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaHotelRoomRepository extends CrudRepository<HotelRoom, UUID> {
}
