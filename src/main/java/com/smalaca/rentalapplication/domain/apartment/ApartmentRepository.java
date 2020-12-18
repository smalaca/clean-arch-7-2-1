package com.smalaca.rentalapplication.domain.apartment;

public interface ApartmentRepository {
    void save(Apartment apartment);

    Apartment findById(String id);
}
