package com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaBookingRepository implements BookingRepository {
    private final SpringJpaBookingRepository springJpaBookingRepository;

    JpaBookingRepository(SpringJpaBookingRepository springJpaBookingRepository) {
        this.springJpaBookingRepository = springJpaBookingRepository;
    }

    @Override
    public String save(Booking booking) {
        return springJpaBookingRepository.save(booking).id();
    }

    @Override
    public Booking findById(String bookingId) {
        return springJpaBookingRepository.findById(UUID.fromString(bookingId)).get();
    }
}
