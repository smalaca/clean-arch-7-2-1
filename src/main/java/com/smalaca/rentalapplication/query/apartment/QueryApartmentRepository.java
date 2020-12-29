package com.smalaca.rentalapplication.query.apartment;

import static java.util.Collections.emptyList;

public class QueryApartmentRepository {
    private final SpringQueryApartmentRepository springQueryApartmentRepository;
    private final SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository;

    public QueryApartmentRepository(
            SpringQueryApartmentRepository springQueryApartmentRepository, SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository) {
        this.springQueryApartmentRepository = springQueryApartmentRepository;
        this.springQueryApartmentBookingHistoryRepository = springQueryApartmentBookingHistoryRepository;
    }

    public Iterable<ApartmentReadModel> findAll() {
        return emptyList();
    }

    public ApartmentDetails findById(String id) {
        return null;
    }
}
