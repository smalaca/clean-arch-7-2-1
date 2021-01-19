package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.owner.OwnerRepository;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;

public class ApartmentFactory {
    private final OwnerRepository ownerRepository;

    public ApartmentFactory(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Apartment create(NewApartmentDto apartmentDto) {
        if (ownerRepository.exists(apartmentDto.getOwnerId())) {
            return apartment()
                    .withOwnerId(apartmentDto.getOwnerId())
                    .withStreet(apartmentDto.getStreet())
                    .withPostalCode(apartmentDto.getPostalCode())
                    .withHouseNumber(apartmentDto.getHouseNumber())
                    .withApartmentNumber(apartmentDto.getApartmentNumber())
                    .withCity(apartmentDto.getCity())
                    .withCountry(apartmentDto.getCountry())
                    .withDescription(apartmentDto.getDescription())
                    .withSpacesDefinition(apartmentDto.getSpacesDefinition())
                    .build();
        } else {
            throw new OwnerDoesNotExistException(apartmentDto.getOwnerId());
        }
    }
}
