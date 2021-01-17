package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.period.Period;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
@SuppressWarnings("PMD.UnusedPrivateField")
public class ApartmentBookingHistory {
    @Id
    private String apartmentId;

    @ElementCollection
    @CollectionTable(name = "APARTMENT_BOOKING", joinColumns = @JoinColumn(name = "APARTMENT_ID"))
    private List<ApartmentBooking> bookings = new ArrayList<>();

    private ApartmentBookingHistory() {}

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void addBookingStart(LocalDateTime bookingDateTime, String ownerId, String tenantId, Period period) {
        add(ApartmentBooking.start(bookingDateTime, ownerId, tenantId, period));
    }

    private void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApartmentBookingHistory that = (ApartmentBookingHistory) o;

        return new EqualsBuilder().append(apartmentId, that.apartmentId).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(apartmentId).toHashCode();
    }
}
