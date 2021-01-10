package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaHotelRoomOfferRepository implements HotelRoomOfferRepository {
    private final SpringJpaHotelRoomOfferRepository repository;

    JpaHotelRoomOfferRepository(SpringJpaHotelRoomOfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(HotelRoomOffer hotelRoomOffer) {
        return repository.save(hotelRoomOffer).id();
    }
}
