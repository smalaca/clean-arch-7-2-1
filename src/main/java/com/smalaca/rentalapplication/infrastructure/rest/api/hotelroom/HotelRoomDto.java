package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import java.util.Map;

class HotelRoomDto {
    private final String hotelId;
    private final int number;
    private final Map<String, Double> spacesDefinition;
    private final String description;

    HotelRoomDto(String hotelId, int number, Map<String, Double> spacesDefinition, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spacesDefinition = spacesDefinition;
        this.description = description;
    }

    String getHotelId() {
        return hotelId;
    }

    int getNumber() {
        return number;
    }

    Map<String, Double> getSpacesDefinition() {
        return spacesDefinition;
    }

    String getDescription() {
        return description;
    }
}
