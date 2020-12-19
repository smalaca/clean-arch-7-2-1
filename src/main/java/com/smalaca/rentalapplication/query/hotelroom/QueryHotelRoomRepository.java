package com.smalaca.rentalapplication.query.hotelroom;

public class QueryHotelRoomRepository {
    private final SpringJpaQueryHotelRoomRepository repository;

    public QueryHotelRoomRepository(SpringJpaQueryHotelRoomRepository repository) {
        this.repository = repository;
    }

    public Iterable<HotelRoomReadModel> findAll(String hotelId) {
        return repository.findAllByHotelId(hotelId);
    }
}
