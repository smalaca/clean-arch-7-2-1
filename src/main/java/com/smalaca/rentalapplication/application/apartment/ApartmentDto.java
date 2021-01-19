package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.NewApartmentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ApartmentDto {
    private final String ownerId;
    private final String street;
    private final String postalCode;
    private final String houseNumber;
    private final String apartmentNumber;
    private final String city;
    private final String country;
    private final String description;
    private final Map<String, Double> spacesDefinition;

    NewApartmentDto asNewApartmentDto() {
        return NewApartmentDto.builder()
                .ownerId(ownerId)
                .street(street)
                .postalCode(postalCode)
                .houseNumber(houseNumber)
                .apartmentNumber(apartmentNumber)
                .city(city)
                .country(country)
                .description(description)
                .spacesDefinition(spacesDefinition)
                .build();
    }
}
