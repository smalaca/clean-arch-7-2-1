package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.period.Period;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class ApartmentBooking {
    @Enumerated(EnumType.STRING)
    private BookingStep bookingStep;
    private LocalDateTime bookingDateTime;
    private String ownerId;
    private String tenantId;

    @Embedded
    private Period period;

    private ApartmentBooking() {}

    private ApartmentBooking(BookingStep bookingStep, LocalDateTime bookingDateTime, String ownerId, String tenantId, Period period) {
        this.bookingStep = bookingStep;
        this.bookingDateTime = bookingDateTime;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.period = period;
    }

    static ApartmentBooking start(LocalDateTime bookingDateTime, String ownerId, String tenantId, Period period) {
        return new ApartmentBooking(BookingStep.START, bookingDateTime, ownerId, tenantId, period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApartmentBooking that = (ApartmentBooking) o;

        return new EqualsBuilder()
                .append(bookingStep, that.bookingStep)
                .append(bookingDateTime, that.bookingDateTime)
                .append(ownerId, that.ownerId)
                .append(tenantId, that.tenantId)
                .append(period, that.period)
                .isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(bookingStep)
                .append(bookingDateTime)
                .append(ownerId)
                .append(tenantId)
                .append(period)
                .toHashCode();
    }
}
