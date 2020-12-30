package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class HotelBookingHistory {
    @Id
    private String hotelId;

    @OneToMany
    private List<HotelRoomBookingHistory> hotelRoomBookingHistories = new ArrayList<>();

    private HotelBookingHistory() {}

    public HotelBookingHistory(String hotelId) {
        this.hotelId = hotelId;
    }

    public void add(String hotelRoomId, LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        HotelRoomBookingHistory hotelRoomBookingHistory = findFor(hotelRoomId);
        hotelRoomBookingHistory.add(bookingDateTime, tenantId, days);
    }

    private HotelRoomBookingHistory findFor(String hotelRoomId) {
        Optional<HotelRoomBookingHistory> history = hotelRoomBookingHistories.stream()
                .filter(hotelRoomBookingHistory -> hotelRoomBookingHistory.hasIdEqualTo(hotelRoomId))
                .findFirst();

        if (history.isEmpty()) {
            HotelRoomBookingHistory hotelRoomBookingHistory = new HotelRoomBookingHistory(hotelRoomId);
            hotelRoomBookingHistories.add(hotelRoomBookingHistory);
            return hotelRoomBookingHistory;
        } else {
            return history.get();
        }
    }
}
