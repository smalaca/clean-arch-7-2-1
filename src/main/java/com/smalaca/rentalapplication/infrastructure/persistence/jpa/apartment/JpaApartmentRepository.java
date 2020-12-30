package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaApartmentRepository implements ApartmentRepository {
    private final SpringJpaApartmentRepository springJpaApartmentRepository;

    JpaApartmentRepository(SpringJpaApartmentRepository springJpaApartmentRepository) {
        this.springJpaApartmentRepository = springJpaApartmentRepository;
    }

    @Override
    public String save(Apartment apartment) {
        return springJpaApartmentRepository.save(apartment).id();
    }

    @Override
    public Apartment findById(String id) {
        return springJpaApartmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ApartmentDoesNotExistException(id));
    }
}
