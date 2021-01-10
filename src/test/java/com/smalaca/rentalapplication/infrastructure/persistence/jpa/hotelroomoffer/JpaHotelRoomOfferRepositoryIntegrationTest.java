package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferAssertion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaHotelRoomOfferRepositoryIntegrationTest {
    private static final String HOTEL_ROOM_ID = "213131";
    private static final BigDecimal PRICE = BigDecimal.valueOf(42.13);
    private static final LocalDate START = LocalDate.of(2040, 12, 10);
    private static final LocalDate END = LocalDate.of(2041, 12, 20);

    @Autowired private JpaHotelRoomOfferRepository jpaHotelRoomOfferRepository;
    @Autowired private SpringJpaHotelRoomOfferRepository springJpaHotelRoomOfferRepository;

    private UUID hotelRoomOfferId;

    @AfterEach
    void deleteHotelRoomOffer() {
        springJpaHotelRoomOfferRepository.deleteById(hotelRoomOfferId);
    }

    @Test
    void shouldSaveHotelRoomOffer() {
        HotelRoomOffer hotelRoomOffer = hotelRoomOffer()
                .withHotelRoomId(HOTEL_ROOM_ID)
                .withPrice(PRICE)
                .withAvailability(START, END)
                .build();

        hotelRoomOfferId = jpaHotelRoomOfferRepository.save(hotelRoomOffer);

        HotelRoomOfferAssertion.assertThat(springJpaHotelRoomOfferRepository.findById(hotelRoomOfferId).get())
                .hasIdEqualTo(hotelRoomOfferId)
                .hasHotelRoomEqualTo(HOTEL_ROOM_ID)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }
}