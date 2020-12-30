package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
public class ApartmentBookingReadModel {
    private String bookingStep;
    private LocalDateTime bookingDateTime;
    private String ownerId;
    private String tenantId;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    private ApartmentBookingReadModel() {}

    ApartmentBookingReadModel(String bookingStep, LocalDateTime bookingDateTime, String ownerId, String tenantId, LocalDate periodStart, LocalDate periodEnd) {
        this.bookingStep = bookingStep;
        this.bookingDateTime = bookingDateTime;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public String getBookingStep() {
        return bookingStep;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }
}
