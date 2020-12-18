package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;

class JpaApartmentBookingHistoryRepository implements ApartmentBookingHistoryRepository {
    private final SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository;

    JpaApartmentBookingHistoryRepository(SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository) {
        this.springJpaApartmentBookingHistoryRepository = springJpaApartmentBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.existsById(apartmentId);
    }

    @Override
    public ApartmentBookingHistory findFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.findById(apartmentId).get();
    }

    @Override
    public void save(ApartmentBookingHistory apartmentBookingHistory) {
        springJpaApartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }
}
