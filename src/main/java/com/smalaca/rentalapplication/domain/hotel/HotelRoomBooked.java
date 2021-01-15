package com.smalaca.rentalapplication.domain.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HotelRoomBooked {
    private final String eventId;
    private final LocalDateTime eventCreationDateTime;
    private final int hotelRoomNumber;
    private final String hotelId;
    private final String tenantId;
    private final List<LocalDate> days;

    HotelRoomBooked(
            String eventId, LocalDateTime eventCreationDateTime, String hotelId, int hotelRoomNumber, String tenantId, List<LocalDate> days) {
        this.eventId = eventId;
        this.eventCreationDateTime = eventCreationDateTime;
        this.hotelRoomNumber = hotelRoomNumber;
        this.hotelId = hotelId;
        this.tenantId = tenantId;
        this.days = days;
    }

    public String getEventId() {
        return eventId;
    }

    public LocalDateTime getEventCreationDateTime() {
        return eventCreationDateTime;
    }

    public int getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<LocalDate> getDays() {
        return days;
    }
}
