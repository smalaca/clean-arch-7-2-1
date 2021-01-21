package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaApartmentOfferRepository extends CrudRepository<ApartmentOffer, UUID> {
    boolean existsByApartmentId(String apartmentId);

    ApartmentOffer findByApartmentId(String apartmentId);
}
