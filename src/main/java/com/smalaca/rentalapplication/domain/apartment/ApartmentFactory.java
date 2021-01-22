package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.address.AddressCatalogue;
import com.smalaca.rentalapplication.domain.address.AddressException;
import com.smalaca.rentalapplication.domain.owner.OwnerRepository;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;

public class ApartmentFactory {
    private final OwnerRepository ownerRepository;
    private final AddressCatalogue addressCatalogue;

    public ApartmentFactory(OwnerRepository ownerRepository, AddressCatalogue addressCatalogue) {
        this.ownerRepository = ownerRepository;
        this.addressCatalogue = addressCatalogue;
    }

    public Apartment create(NewApartmentDto apartmentDto) {
        if (!ownerRepository.exists(apartmentDto.getOwnerId())) {
            throw new OwnerDoesNotExistException(apartmentDto.getOwnerId());
        }

        if (!addressCatalogue.exists(apartmentDto.addressDto())) {
            throw new AddressException(apartmentDto.addressDto());
        }

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
    }
}
