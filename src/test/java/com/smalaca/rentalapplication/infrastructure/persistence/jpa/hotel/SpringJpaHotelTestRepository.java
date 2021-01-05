package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaHotelTestRepository {
    private final SpringJpaHotelRepository repository;

    SpringJpaHotelTestRepository(SpringJpaHotelRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(id -> repository.deleteById(UUID.fromString(id)));
    }
}