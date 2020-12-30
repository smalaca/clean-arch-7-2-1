package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaHotelRepository extends CrudRepository<Hotel, UUID> {
}
