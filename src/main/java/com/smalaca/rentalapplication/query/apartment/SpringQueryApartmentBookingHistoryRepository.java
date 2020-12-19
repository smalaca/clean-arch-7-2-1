package com.smalaca.rentalapplication.query.apartment;

import org.springframework.data.repository.CrudRepository;

interface SpringQueryApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistoryReadModel, String> {
}
