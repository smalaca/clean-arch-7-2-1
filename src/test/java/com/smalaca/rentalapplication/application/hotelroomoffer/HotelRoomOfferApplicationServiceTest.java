package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomNotFoundException;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferAssertion;
import com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import com.smalaca.rentalapplication.domain.hotelroomoffer.NotAllowedMoneyValueException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomOfferApplicationServiceTest {
    private static final String HOTEL_ROOM_ID = "213131";
    private static final BigDecimal PRICE = BigDecimal.valueOf(42);
    private static final LocalDate START = LocalDate.of(2020, 12, 10);
    private static final LocalDate END = LocalDate.of(2021, 12, 20);

    private final HotelRoomRepository hotelRoomRepository = mock(HotelRoomRepository.class);
    private final HotelRoomOfferRepository hotelRoomOfferRepository = mock(HotelRoomOfferRepository.class);
    private final HotelRoomOfferApplicationService service = new HotelRoomOfferApplicationService(hotelRoomOfferRepository, hotelRoomRepository);

    @Test
    void shouldCreateHotelRoomOffer() {
        ArgumentCaptor<HotelRoomOffer> captor = ArgumentCaptor.forClass(HotelRoomOffer.class);
        givenExistingHotelRoom();

        service.add(givenHotelRoomOfferDto());

        then(hotelRoomOfferRepository).should().save(captor.capture());
        HotelRoomOfferAssertion.assertThat(captor.getValue())
                .hasHotelRoomEqualTo(HOTEL_ROOM_ID)
                .hasPriceEqualTo(PRICE)
                .hasAvailabilityEqualTo(START, END);
    }

    @Test
    void shouldRecognizeHotelRoomDoesNotExist() {
        givenNotExistingHotelRoom();

        HotelRoomNotFoundException actual = assertThrows(HotelRoomNotFoundException.class, () -> service.add(givenHotelRoomOfferDto()));

        assertThat(actual).hasMessage("Hotel room with id: " + HOTEL_ROOM_ID + " does not exist.");
    }

    @Test
    void shouldRecognizePriceIsNotHigherThanZero() {
        HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, START, END);
        givenExistingHotelRoom();

        NotAllowedMoneyValueException actual = assertThrows(NotAllowedMoneyValueException.class, () -> service.add(dto));

        assertThat(actual).hasMessage("Price 0 is not greater than zero.");
    }

    private HotelRoomOfferDto givenHotelRoomOfferDto() {
        return new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, START, END);
    }

    private void givenNotExistingHotelRoom() {
        given(hotelRoomRepository.existById(HOTEL_ROOM_ID)).willReturn(false);
    }

    private void givenExistingHotelRoom() {
        given(hotelRoomRepository.existById(HOTEL_ROOM_ID)).willReturn(true);
    }
}
