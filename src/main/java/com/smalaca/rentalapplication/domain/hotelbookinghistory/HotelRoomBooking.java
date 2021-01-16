package com.smalaca.rentalapplication.domain.hotelbookinghistory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HotelRoomBooking that = (HotelRoomBooking) o;

        return new EqualsBuilder().append(bookingDateTime, that.bookingDateTime).append(tenantId, that.tenantId).append(days, that.days).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(bookingDateTime).append(tenantId).append(days).toHashCode();
    }
}
