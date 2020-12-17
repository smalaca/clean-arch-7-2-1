package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaHotelRepository extends CrudRepository<Hotel, String> {
}
