package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
class HotelRoomBooking {
    private final LocalDateTime bookingDateTime;
    private final String tenantId;
    private final List<LocalDate> days;

    HotelRoomBooking(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        this.bookingDateTime = bookingDateTime;
        this.tenantId = tenantId;
        this.days = days;
    }
}
