package com.smalaca.rentalapplication.query.apartment;

public class QueryApartmentRepository {
    private final SpringQueryApartmentRepository repository;

    public QueryApartmentRepository(SpringQueryApartmentRepository repository) {
        this.repository = repository;
    }

    public Iterable<ApartmentReadModel> findAll() {
        return repository.findAll();
    }
}
