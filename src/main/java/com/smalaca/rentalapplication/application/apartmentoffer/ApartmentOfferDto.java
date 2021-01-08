package com.smalaca.rentalapplication.application.apartmentoffer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
class ApartmentOfferDto {
    private final String apartmentId;
    private final BigDecimal price;
    private final LocalDate start;
    private final LocalDate end;
}
