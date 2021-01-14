package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringJpaApartmentBookingHistoryTestRepository {
    private final SpringJpaApartmentBookingHistoryRepository repository;

    SpringJpaApartmentBookingHistoryTestRepository(SpringJpaApartmentBookingHistoryRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String apartmentId) {
        repository.deleteById(apartmentId);
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(this::deleteById);
    }
}