package com.smalaca.rentalapplication.domain.booking;

import static com.smalaca.rentalapplication.domain.booking.RentalType.HOTEL_ROOM;

public class RentalPlaceIdentifierTestFactory {

    public static RentalPlaceIdentifier hotelRoom(String rentalPlaceId) {
        return new RentalPlaceIdentifier(HOTEL_ROOM, rentalPlaceId);
    }
}