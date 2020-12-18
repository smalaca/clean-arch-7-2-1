package com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {
}
