package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SpringJpaHotelRoomTestRepository {
    private final SpringJpaHotelRoomRepository repository;

    SpringJpaHotelRoomTestRepository(SpringJpaHotelRoomRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String hotelRoomId) {
        repository.deleteById(UUID.fromString(hotelRoomId));
    }
}