package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HotelBookingHistory {
    private final String hotelId;

    public HotelBookingHistory(String hotelId) {
        this.hotelId = hotelId;
    }

    public void add(String hotelRoomId, LocalDateTime eventCreationDateTime, String tenantId, List<LocalDate> days) {

    }
}
