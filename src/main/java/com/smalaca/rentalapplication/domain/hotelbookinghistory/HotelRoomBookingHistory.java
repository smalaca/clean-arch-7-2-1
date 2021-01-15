package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
class HotelRoomBookingHistory {
    @Id
    @GeneratedValue
    private UUID id;

    private int hotelRoomNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HotelRoomBooking> bookings = new ArrayList<>();

    private HotelRoomBookingHistory() {}

    HotelRoomBookingHistory(int hotelRoomNumber) {
        this.hotelRoomNumber = hotelRoomNumber;
    }

    boolean hasNumberEqualTo(int hotelRoomId) {
        return this.hotelRoomNumber == hotelRoomId;
    }

    void add(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        bookings.add(new HotelRoomBooking(bookingDateTime, tenantId, days));
    }
}
