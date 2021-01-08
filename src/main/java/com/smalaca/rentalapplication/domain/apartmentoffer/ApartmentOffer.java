package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApartmentOffer {
    private final String apartmentId;
    private final Money money;
    private final ApartmentAvailability availability;

    private ApartmentOffer(String apartmentId, Money money, ApartmentAvailability availability) {
        this.apartmentId = apartmentId;
        this.money = money;
        this.availability = availability;
    }

    public static class Builder {
        private String apartmentId;
        private BigDecimal price;
        private LocalDate start;
        private LocalDate end;

        private Builder() {}
        
        public static Builder apartmentOffer() {
            return new Builder();
        }

        public Builder withApartmentId(String apartmentId) {
            this.apartmentId = apartmentId;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withAvailability(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
            return this;
        }

        public ApartmentOffer build() {
            return new ApartmentOffer(apartmentId, money(), availability());
        }

        private ApartmentAvailability availability() {
            return ApartmentAvailability.of(start, end);
        }

        private Money money() {
            return Money.of(price);
        }
    }
}
