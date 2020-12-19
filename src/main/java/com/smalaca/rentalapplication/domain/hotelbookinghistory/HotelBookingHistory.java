package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class HotelBookingHistory {
    @Id
    private final String hotelId;

    public HotelBookingHistory(String hotelId) {
        this.hotelId = hotelId;
    }

    public void add(String hotelRoomId, LocalDateTime eventCreationDateTime, String tenantId, List<LocalDate> days) {

    }
}
