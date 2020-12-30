package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaApartmentRepository extends CrudRepository<Apartment, UUID> {
}
