package com.smalaca.rentalapplication.domain.apartmentoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApartmentOfferTestBuilder {
    private final ApartmentOffer.Builder apartmentOffer;

    private ApartmentOfferTestBuilder(ApartmentOffer.Builder apartmentOffer) {
        this.apartmentOffer = apartmentOffer;
    }

    public static ApartmentOfferTestBuilder apartmentOffer() {
        return new ApartmentOfferTestBuilder(ApartmentOffer.Builder.apartmentOffer());
    }

    public ApartmentOfferTestBuilder withApartmentId(String apartmentId) {
        apartmentOffer.withApartmentId(apartmentId);
        return this;
    }

    public ApartmentOfferTestBuilder withPrice(BigDecimal price) {
        apartmentOffer.withPrice(price);
        return this;
    }

    public ApartmentOfferTestBuilder withAvailability(LocalDate start, LocalDate end) {
        apartmentOffer.withAvailability(start, end);
        return this;
    }

    public ApartmentOffer build() {
        return apartmentOffer.build();
    }

}
