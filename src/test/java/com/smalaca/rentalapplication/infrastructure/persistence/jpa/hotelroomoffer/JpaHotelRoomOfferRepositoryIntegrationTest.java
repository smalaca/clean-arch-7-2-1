package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferAssertion;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferTestFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaHotelRoomOfferRepositoryIntegrationTest {
    private static final String HOTEL_ID = "1234";
    private static final int HOTEL_ROOM_NUMBER = 213131;
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
        HotelRoomOffer hotelRoomOffer = HotelRoomOfferTestFactory.create(HOTEL_ID, HOTEL_ROOM_NUMBER, PRICE, START, END);

        hotelRoomOfferId = jpaHotelRoomOfferRepository.save(hotelRoomOffer);

        HotelRoomOfferAssertion.assertThat(springJpaHotelRoomOfferRepository.findById(hotelRoomOfferId).get())
                .hasIdEqualTo(hotelRoomOfferId)
                .hasHotelIdEqualTo(HOTEL_ID)
                .hasHotelRoomNumberEqualTo(HOTEL_ROOM_NUMBER)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }
}