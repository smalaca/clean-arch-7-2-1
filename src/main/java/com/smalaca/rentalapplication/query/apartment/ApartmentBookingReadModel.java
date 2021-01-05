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
