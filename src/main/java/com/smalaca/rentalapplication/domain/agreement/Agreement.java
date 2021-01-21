package com.smalaca.rentalapplication.domain.agreement;

import com.smalaca.rentalapplication.domain.booking.RentalType;
import com.smalaca.rentalapplication.domain.money.Money;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class Agreement {
    @Id
    @GeneratedValue
    private UUID id;

    private RentalType rentalType;
    private String rentalPlaceId;
    private String ownerId;
    private String tenantId;
    @ElementCollection
    private List<LocalDate> days;
    @Embedded
    private Money price;

    public Agreement() {}

    private Agreement(RentalType rentalType, String rentalPlaceId, String ownerId, String tenantId, List<LocalDate> days, Money price) {
        this.rentalType = rentalType;
        this.rentalPlaceId = rentalPlaceId;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.days = days;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Agreement agreement = (Agreement) o;

        return new EqualsBuilder()
                .append(rentalType, agreement.rentalType)
                .append(rentalPlaceId, agreement.rentalPlaceId)
                .append(ownerId, agreement.ownerId)
                .append(tenantId, agreement.tenantId)
                .append(days, agreement.days)
                .append(price, agreement.price)
                .isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rentalType)
                .append(rentalPlaceId)
                .append(ownerId)
                .append(tenantId)
                .append(days)
                .append(price)
                .toHashCode();
    }

    public void accept(AgreementEventsPublisher agreementEventsPublisher) {
        agreementEventsPublisher.agreementAccepted(rentalType, rentalPlaceId, ownerId, tenantId, days, price);
    }

    public static class Builder {
        private RentalType rentalType;
        private String rentalPlaceId;
        private String ownerId;
        private String tenantId;
        private Money price;
        private List<LocalDate> days;

        private Builder() {}

        public static Builder agreement() {
            return new Builder();
        }

        public Builder withRentalType(RentalType rentalType) {
            this.rentalType = rentalType;
            return this;
        }

        public Builder withRentalPlaceId(String rentalPlaceId) {
            this.rentalPlaceId = rentalPlaceId;
            return this;
        }

        public Builder withOwnerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder withTenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder withDays(List<LocalDate> days) {
            this.days = days;
            return this;
        }

        public Builder withPrice(Money price) {
            this.price = price;
            return this;
        }

        public Agreement build() {
            return new Agreement(rentalType, rentalPlaceId, ownerId, tenantId, days, price);
        }
    }
}
