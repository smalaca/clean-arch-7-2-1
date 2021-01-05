package com.smalaca.rentalapplication.domain.apartmentbookinghistory;

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
    private BookingPeriod bookingPeriod;

    private ApartmentBooking() {}

    private ApartmentBooking(BookingStep bookingStep, LocalDateTime bookingDateTime, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        this.bookingStep = bookingStep;
        this.bookingDateTime = bookingDateTime;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.bookingPeriod = bookingPeriod;
    }

    public static ApartmentBooking start(LocalDateTime bookingDateTime, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        return new ApartmentBooking(BookingStep.START, bookingDateTime, ownerId, tenantId, bookingPeriod);
    }
}
