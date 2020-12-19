package com.smalaca.rentalapplication.query.hotelroom;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface SpringJpaQueryHotelRoomRepository extends CrudRepository<HotelRoomReadModel, String> {
    List<HotelRoomReadModel> findAllByHotelId(String hotelId);
}
