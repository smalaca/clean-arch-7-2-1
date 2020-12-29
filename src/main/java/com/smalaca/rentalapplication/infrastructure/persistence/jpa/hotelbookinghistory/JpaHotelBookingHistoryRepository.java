package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

import com.smalaca.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import com.smalaca.rentalapplication.domain.hotelbookinghistory.HotelBookingHistoryRepository;

class JpaHotelBookingHistoryRepository implements HotelBookingHistoryRepository {
    private final SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository;

    JpaHotelBookingHistoryRepository(SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository) {
        this.springJpaHotelBookingHistoryRepository = springJpaHotelBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String hotelId) {
        return false;
    }

    @Override
    public HotelBookingHistory findFor(String hotelId) {
        return null;
    }

    @Override
    public void save(HotelBookingHistory hotelBookingHistory) {

    }
}
