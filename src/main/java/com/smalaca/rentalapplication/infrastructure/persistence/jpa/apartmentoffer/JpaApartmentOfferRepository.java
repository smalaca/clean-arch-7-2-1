package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaApartmentOfferRepository implements ApartmentOfferRepository {
    private final SpringJpaApartmentOfferRepository repository;

    JpaApartmentOfferRepository(SpringJpaApartmentOfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(ApartmentOffer apartmentOffer) {
        return repository.save(apartmentOffer).id();
    }
}
