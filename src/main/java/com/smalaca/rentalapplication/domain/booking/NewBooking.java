package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.rentalplace.RentalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.smalaca.rentalapplication.domain.rentalplace.RentalType.APARTMENT;
import static com.smalaca.rentalapplication.domain.rentalplace.RentalType.HOTEL_ROOM;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PACKAGE)
public class NewBooking {
    private final RentalType rentalType;
    private final String rentalPlaceId;
    private final String tenantId;
    private final String ownerId;
    private final Money price;
    private final List<LocalDate> days;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public static NewBooking forApartment(String rentalPlaceId, String tenantId, String ownerId, Money price, Period period) {
        return new NewBooking(APARTMENT, rentalPlaceId, tenantId, ownerId, price, period.asDays());
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    public static NewBooking forHotelRoom(String rentalPlaceId, String tenantId, String ownerId, Money price, List<LocalDate> days) {
        return new NewBooking(HOTEL_ROOM, rentalPlaceId, tenantId, ownerId, price, days);
    }
}
