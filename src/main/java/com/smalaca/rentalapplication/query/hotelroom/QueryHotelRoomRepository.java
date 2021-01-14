package com.smalaca.rentalapplication.query.hotelroom;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class QueryHotelRoomRepository {
    private final SpringJpaQueryHotelRoomRepository repository;

    public QueryHotelRoomRepository(SpringJpaQueryHotelRoomRepository repository) {
        this.repository = repository;
    }

    public Iterable<HotelRoomReadModel> findAll(String hotelId) {
        return repository.findAllByHotelId(UUID.fromString(hotelId));
    }
}
