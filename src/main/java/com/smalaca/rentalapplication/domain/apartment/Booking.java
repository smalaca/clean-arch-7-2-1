package com.smalaca.rentalapplication.domain.apartment;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private String id;
    private final String apartmentId;
    private final String tenantId;
    @Embedded
    private final Period period;

    Booking(String apartmentId, String tenantId, Period period) {
        this.apartmentId = apartmentId;
        this.tenantId = tenantId;
        this.period = period;
    }
}
