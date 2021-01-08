package com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaBookingRepository extends CrudRepository<Booking, UUID> {
}
