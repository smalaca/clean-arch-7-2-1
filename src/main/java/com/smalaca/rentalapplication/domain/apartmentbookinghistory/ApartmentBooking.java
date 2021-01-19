package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.period.Period;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
@EqualsAndHashCode
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
}
