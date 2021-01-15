package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class HotelBookingHistory {
    @Id
    private String hotelId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HotelRoomBookingHistory> hotelRoomBookingHistories = new ArrayList<>();

    private HotelBookingHistory() {}

    public HotelBookingHistory(String hotelId) {
        this.hotelId = hotelId;
    }

    public void add(int hotelRoomNumber, LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        HotelRoomBookingHistory hotelRoomBookingHistory = findFor(hotelRoomNumber);
        hotelRoomBookingHistory.add(bookingDateTime, tenantId, days);
    }

    private HotelRoomBookingHistory findFor(int hotelRoomNumber) {
        Optional<HotelRoomBookingHistory> history = hotelRoomBookingHistories.stream()
                .filter(hotelRoomBookingHistory -> hotelRoomBookingHistory.hasNumberEqualTo(hotelRoomNumber))
                .findFirst();

        if (history.isEmpty()) {
            HotelRoomBookingHistory hotelRoomBookingHistory = new HotelRoomBookingHistory(hotelRoomNumber);
            hotelRoomBookingHistories.add(hotelRoomBookingHistory);
            return hotelRoomBookingHistory;
        } else {
            return history.get();
        }
    }
}
