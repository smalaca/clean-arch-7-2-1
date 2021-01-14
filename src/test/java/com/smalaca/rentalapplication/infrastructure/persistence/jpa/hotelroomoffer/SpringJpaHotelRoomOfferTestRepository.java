package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaHotelRoomOfferTestRepository {
    private final SpringJpaHotelRoomOfferRepository repository;

    SpringJpaHotelRoomOfferTestRepository(SpringJpaHotelRoomOfferRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(this::deleteById);
    }

    private void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}