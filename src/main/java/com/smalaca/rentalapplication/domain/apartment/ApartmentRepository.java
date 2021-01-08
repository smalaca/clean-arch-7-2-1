package com.smalaca.rentalapplication.domain.apartment;

public interface ApartmentRepository {
    String save(Apartment apartment);

    Apartment findById(String id);

    boolean existById(String apartmentId);
}
