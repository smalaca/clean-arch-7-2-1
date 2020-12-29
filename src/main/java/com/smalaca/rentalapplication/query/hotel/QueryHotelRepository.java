package com.smalaca.rentalapplication.query.hotel;

import static java.util.Collections.emptyList;

public class QueryHotelRepository {
    private final SpringJpaQueryHotelRepository repository;

    public QueryHotelRepository(SpringJpaQueryHotelRepository repository) {
        this.repository = repository;
    }

    public Iterable<HotelReadModel> findAll() {
        return emptyList();
    }
}
