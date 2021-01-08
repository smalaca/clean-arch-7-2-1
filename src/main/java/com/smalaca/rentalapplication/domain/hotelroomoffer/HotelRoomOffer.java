package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HotelRoomOffer {
    private final String hotelRoomId;
    private final Money money;
    private final HotelRoomAvailability availability;

    private HotelRoomOffer(String hotelRoomId, Money money, HotelRoomAvailability availability) {
        this.hotelRoomId = hotelRoomId;
        this.money = money;
        this.availability = availability;
    }

    public static class Builder {
        private static final LocalDate NO_END_DATE = null;

        private String hotelRoomId;
        private BigDecimal price;
        private LocalDate start;
        private LocalDate end;

        private Builder() {}

        public static Builder hotelRoomOffer() {
            return new Builder();
        }

        public Builder withHotelRoomId(String hotelRoomId) {
            this.hotelRoomId = hotelRoomId;
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

        public HotelRoomOffer build() {
            return new HotelRoomOffer(hotelRoomId, money(), hotelRoomAvailability());
        }

        private HotelRoomAvailability hotelRoomAvailability() {
            if (end == NO_END_DATE) {
                return HotelRoomAvailability.fromStart(start);
            }

            return HotelRoomAvailability.from(start, end);
        }

        private Money money() {
            return Money.of(price);
        }
    }
}
