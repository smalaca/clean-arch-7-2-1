package com.smalaca.rentalapplication.domain.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RentalPlaceIdentifier {
    private final RentalType rentalType;
    private final String rentalPlaceId;

    public RentalPlaceIdentifier(RentalType rentalType, String rentalPlaceId) {
        this.rentalType = rentalType;
        this.rentalPlaceId = rentalPlaceId;
    }

    public static RentalPlaceIdentifier apartment(String id) {
        return new RentalPlaceIdentifier(RentalType.APARTMENT, id);
    }
}
