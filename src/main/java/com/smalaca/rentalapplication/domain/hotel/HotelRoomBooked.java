package com.smalaca.rentalapplication.domain.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HotelRoomBooked {
    private final String eventId;
    private final LocalDateTime eventCreationDateTime;
    private final String hotelRoomId;
    private final String hotelId;
    private final String tenantId;
    private final List<LocalDate> days;

    HotelRoomBooked(
            String eventId, LocalDateTime eventCreationDateTime, String hotelRoomId, String hotelId, String tenantId, List<LocalDate> days) {
        this.eventId = eventId;
        this.eventCreationDateTime = eventCreationDateTime;
        this.hotelRoomId = hotelRoomId;
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

    public String getHotelRoomId() {
        return hotelRoomId;
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
