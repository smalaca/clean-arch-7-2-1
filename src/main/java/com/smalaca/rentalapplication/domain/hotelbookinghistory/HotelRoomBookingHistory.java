package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
@SuppressWarnings("PMD.UnusedPrivateField")
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

    boolean hasNumberEqualTo(int hotelRoomNumber) {
        return this.hotelRoomNumber == hotelRoomNumber;
    }

    void add(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        bookings.add(new HotelRoomBooking(bookingDateTime, tenantId, days));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HotelRoomBookingHistory that = (HotelRoomBookingHistory) o;

        return new EqualsBuilder().append(hotelRoomNumber, that.hotelRoomNumber).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(hotelRoomNumber).toHashCode();
    }
}
