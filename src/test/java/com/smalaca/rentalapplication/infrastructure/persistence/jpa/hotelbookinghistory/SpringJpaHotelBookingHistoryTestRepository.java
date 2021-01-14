package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringJpaHotelBookingHistoryTestRepository {
    private final SpringJpaHotelBookingHistoryRepository repository;

    SpringJpaHotelBookingHistoryTestRepository(SpringJpaHotelBookingHistoryRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String hotelId) {
        repository.deleteById(hotelId);
    }

    public void deleteAll(List<String> hotelIds) {
        hotelIds.forEach(this::deleteById);
    }
}