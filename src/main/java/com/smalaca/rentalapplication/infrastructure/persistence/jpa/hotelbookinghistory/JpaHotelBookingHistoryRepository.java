package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

import com.smalaca.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import com.smalaca.rentalapplication.domain.hotelbookinghistory.HotelBookingHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaHotelBookingHistoryRepository implements HotelBookingHistoryRepository {
    private final SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository;

    JpaHotelBookingHistoryRepository(SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository) {
        this.springJpaHotelBookingHistoryRepository = springJpaHotelBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String hotelId) {
        return springJpaHotelBookingHistoryRepository.existsById(hotelId);
    }

    @Override
    public HotelBookingHistory findFor(String hotelId) {
        return springJpaHotelBookingHistoryRepository.findById(hotelId).get();
    }

    @Override
    public void save(HotelBookingHistory hotelBookingHistory) {
        springJpaHotelBookingHistoryRepository.save(hotelBookingHistory);
    }
}
