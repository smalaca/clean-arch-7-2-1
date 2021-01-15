package com.smalaca.rentalapplication.domain.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HotelRoomBookedTestFactory {
    public static HotelRoomBooked create(
            String eventId, LocalDateTime eventCreationDateTime, String hotelId, int hotelRoomNumber, String tenantId, List<LocalDate> days) {
        return new HotelRoomBooked(eventId, eventCreationDateTime, hotelId, hotelRoomNumber, tenantId, days);
    }
}