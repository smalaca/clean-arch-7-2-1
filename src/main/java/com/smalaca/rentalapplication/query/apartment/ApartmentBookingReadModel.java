package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "APARTMENT_BOOKING")
public class ApartmentBookingReadModel {
    private final String bookingStep;
    private final LocalDateTime bookingDateTime;
    private final String ownerId;
    private final String tenantId;
    private final LocalDate periodStart;
    private final LocalDate periodEnd;

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
