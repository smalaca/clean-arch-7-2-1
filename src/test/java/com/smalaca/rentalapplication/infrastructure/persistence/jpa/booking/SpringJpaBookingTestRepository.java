package com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaBookingTestRepository {
    private final SpringJpaBookingRepository repository;

    SpringJpaBookingTestRepository(SpringJpaBookingRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String bookingId) {
        repository.deleteById(UUID.fromString(bookingId));
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(this::deleteById);
    }
}