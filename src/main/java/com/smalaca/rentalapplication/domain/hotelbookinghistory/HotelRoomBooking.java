package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
class HotelRoomBooking {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime bookingDateTime;
    private String tenantId;

    @ElementCollection
    private List<LocalDate> days;

    private HotelRoomBooking() {}

    HotelRoomBooking(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        this.bookingDateTime = bookingDateTime;
        this.tenantId = tenantId;
        this.days = days;
    }
}
