package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferAssertion;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomOfferApplicationServiceTest {
    private static final String HOTEL_ROOM_ID = "213131";
    private static final BigDecimal PRICE = BigDecimal.valueOf(42);
    private static final LocalDate START = LocalDate.of(2020, 12, 10);
    private static final LocalDate END = LocalDate.of(2021, 12, 20);

    private final HotelRoomOfferRepository repository = mock(HotelRoomOfferRepository.class);
    private final HotelRoomOfferApplicationService service = new HotelRoomOfferApplicationService(repository);

    @Test
    void shouldCreateHotelRoomOffer() {
        ArgumentCaptor<HotelRoomOffer> captor = ArgumentCaptor.forClass(HotelRoomOffer.class);
        HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, START, END);
        givenExistingHotelRoom();

        service.add(dto);

        then(repository).should().save(captor.capture());
        HotelRoomOfferAssertion.assertThat(captor.getValue())
                .hasHotelRoomEqualTo(HOTEL_ROOM_ID)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }

    private void givenExistingHotelRoom() {

    }
}
