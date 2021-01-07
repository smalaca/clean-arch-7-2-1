package com.smalaca.rentalapplication.infrastructure.rest.api.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HotelDto {
    private final String name;
    private final String street;
    private final String postalCode;
    private final String buildingNumber;
    private final String city;
    private final String country;
}
