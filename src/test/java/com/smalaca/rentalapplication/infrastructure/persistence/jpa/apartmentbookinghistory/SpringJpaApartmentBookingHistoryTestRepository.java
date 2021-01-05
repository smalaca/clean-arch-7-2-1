package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import org.springframework.stereotype.Repository;

@Repository
public class SpringJpaApartmentBookingHistoryTestRepository {
    private final SpringJpaApartmentBookingHistoryRepository repository;

    SpringJpaApartmentBookingHistoryTestRepository(SpringJpaApartmentBookingHistoryRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String apartmentId) {
        repository.deleteById(apartmentId);
    }
}