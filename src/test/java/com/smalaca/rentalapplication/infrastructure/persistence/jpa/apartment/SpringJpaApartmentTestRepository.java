package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartment;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaApartmentTestRepository {
    private final SpringJpaApartmentRepository repository;

    SpringJpaApartmentTestRepository(SpringJpaApartmentRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String apartmentId) {
        repository.deleteById(UUID.fromString(apartmentId));
    }

    void deleteAll(List<String> apartmentIds) {
        apartmentIds.forEach(this::deleteById);
    }
}