package com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking;

import com.smalaca.rentalapplication.domain.apartment.Booking;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;

class JpaBookingRepository implements BookingRepository {
    private final SpringJpaBookingRepository springJpaBookingRepository;

    JpaBookingRepository(SpringJpaBookingRepository springJpaBookingRepository) {
        this.springJpaBookingRepository = springJpaBookingRepository;
    }

    @Override
    public void save(Booking booking) {
        springJpaBookingRepository.save(booking);
    }
}
