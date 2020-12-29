package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;

class JpaApartmentRepository implements ApartmentRepository {
    private final SpringJpaApartmentRepository springJpaApartmentRepository;

    JpaApartmentRepository(SpringJpaApartmentRepository springJpaApartmentRepository) {
        this.springJpaApartmentRepository = springJpaApartmentRepository;
    }

    @Override
    public void save(Apartment apartment) {

    }

    @Override
    public Apartment findById(String id) {
        return null;
    }
}
