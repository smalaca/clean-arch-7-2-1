package com.smalaca.rentalapplication.application.apartment;

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
    private final Map<String, Double> roomsDefinition;
}
