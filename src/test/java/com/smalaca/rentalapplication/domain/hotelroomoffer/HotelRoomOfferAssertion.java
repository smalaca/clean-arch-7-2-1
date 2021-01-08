package com.smalaca.rentalapplication.domain.hotelroomoffer;

import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HotelRoomOfferAssertion {
    private final HotelRoomOffer actual;

    private HotelRoomOfferAssertion(HotelRoomOffer actual) {
        this.actual = actual;
    }

    public static HotelRoomOfferAssertion assertThat(HotelRoomOffer actual) {
        return new HotelRoomOfferAssertion(actual);
    }

    public HotelRoomOfferAssertion hasHotelRoomEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("hotelRoomId", expected);
        return this;
    }

    public HotelRoomOfferAssertion hasPriceEqualTo(BigDecimal expected) {
        Assertions.assertThat(actual).extracting("money")
                .hasFieldOrPropertyWithValue("price", expected);
        return this;
    }

    public HotelRoomOfferAssertion hasAvailabilityEqualTo(LocalDate start, LocalDate end) {
        Assertions.assertThat(actual).extracting("availability")
                .hasFieldOrPropertyWithValue("start", start)
                .hasFieldOrPropertyWithValue("end", end);
        return this;
    }
}
