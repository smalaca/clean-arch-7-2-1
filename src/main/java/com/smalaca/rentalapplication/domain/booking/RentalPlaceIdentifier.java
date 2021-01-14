package com.smalaca.rentalapplication.domain.booking;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RentalPlaceIdentifier {
    private final RentalType rentalType;
    private final String rentalPlaceId;

    RentalPlaceIdentifier(RentalType rentalType, String rentalPlaceId) {
        this.rentalType = rentalType;
        this.rentalPlaceId = rentalPlaceId;
    }
}
