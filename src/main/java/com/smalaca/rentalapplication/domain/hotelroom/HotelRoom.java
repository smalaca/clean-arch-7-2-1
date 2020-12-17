package com.smalaca.rentalapplication.domain.hotelroom;

import java.util.List;

public class HotelRoom {
    private final String hotelId;
    private final int number;
    private final List<Space> spaces;
    private final String description;

    HotelRoom(String hotelId, int number, List<Space> spaces, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spaces = spaces;
        this.description = description;
    }
}
