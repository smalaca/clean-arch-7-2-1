package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.NewApartmentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ApartmentDto {
    private final String ownerId;

    @NotEmpty(message = "street cannot be empty")
    private final String street;

    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "postal code should be in form xx-xxx and contain only numbers")
    private final String postalCode;

    @NotEmpty(message = "house number cannot be empty")
    private final String houseNumber;

    @NotEmpty(message = "apartment number cannot be empty")
    private final String apartmentNumber;

    @NotEmpty(message = "city cannot be empty")
    private final String city;

    @NotEmpty(message = "country cannot be empty")
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
