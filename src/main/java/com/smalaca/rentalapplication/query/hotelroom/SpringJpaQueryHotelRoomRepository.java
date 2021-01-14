package com.smalaca.rentalapplication.query.hotelroom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface SpringJpaQueryHotelRoomRepository extends CrudRepository<HotelRoomReadModel, String> {
    List<HotelRoomReadModel> findAllByHotelId(UUID hotelId);
}
