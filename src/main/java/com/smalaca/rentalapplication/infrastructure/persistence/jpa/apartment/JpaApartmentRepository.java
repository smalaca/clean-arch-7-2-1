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
    public Apartment findById(String apartmentId) {
        return springJpaApartmentRepository.findById(asUUID(apartmentId))
                .orElseThrow(() -> new ApartmentDoesNotExistException(apartmentId));
    }

    @Override
    public boolean existById(String apartmentId) {
        return springJpaApartmentRepository.existsById(asUUID(apartmentId));
    }

    private UUID asUUID(String id) {
        return UUID.fromString(id);
    }
}
