package com.smalaca.rentalapplication.application.hotel;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelAssertion;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotel.HotelRoom;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomAssertion;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomBooked;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static com.smalaca.rentalapplication.domain.hotel.HotelAssertion.assertThat;
import static com.smalaca.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelApplicationServiceTest {
    private static final String NAME = "Great hotel";
    private static final String STREET = "Unknown";
    private static final String POSTAL_CODE = "12-345";
    private static final String BUILDING_NUMBER = "13";
    private static final String CITY = "Somewhere";
    private static final String COUNTRY = "Nowhere";
    private static final String HOTEL_ID = "1234567";
    private static final int ROOM_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";
    private static final String TENANT_ID = "4321";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String HOTEL_ROOM_ID = "7821321";

    private final HotelRepository hotelRepository = mock(HotelRepository.class);
    private final BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
    private final EventChannel eventChannel = Mockito.mock(EventChannel.class);
    private final HotelApplicationService service = new HotelApplicationServiceFactory().hotelApplicationService(
            hotelRepository, bookingRepository, new FakeEventIdFactory(), new FakeClock(), eventChannel);

    @Test
    void shouldCreateHotel() {
        ArgumentCaptor<Hotel> captor = ArgumentCaptor.forClass(Hotel.class);

        service.add(givenHotelDto());

        then(hotelRepository).should().save(captor.capture());
        assertThat(captor.getValue())
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);
    }

    @Test
    void shouldReturnIdOfNewHotel() {
        given(hotelRepository.save(any())).willReturn(HOTEL_ID);

        String actual = service.add(givenHotelDto());

        Assertions.assertThat(actual).isEqualTo(HOTEL_ID);
    }

    private HotelDto givenHotelDto() {
        return new HotelDto(NAME, STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);
    }

    @Test
    void shouldCreateHotelRoom() {
        givenExistingHotel();
        ArgumentCaptor<Hotel> captor = ArgumentCaptor.forClass(Hotel.class);

        service.add(givenHotelRoomDto());

        then(hotelRepository).should().save(captor.capture());
        HotelAssertion.assertThat(captor.getValue())
                .hasOnlyOneHotelRoom(hotelRoom -> {
                    HotelRoomAssertion.assertThat(hotelRoom)
                            .hasRoomNumberEqualTo(ROOM_NUMBER)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                            .hasDescriptionEqualTo(DESCRIPTION);
                });
    }

    private HotelRoomDto givenHotelRoomDto() {
        return new HotelRoomDto(HOTEL_ID, ROOM_NUMBER, SPACES_DEFINITION, DESCRIPTION);
    }

    @Test
    void shouldCreateBookingWhenHotelRoomBooked() {
        Hotel hotel = givenExistingHotel();
        hotel.addRoom(ROOM_NUMBER, SPACES_DEFINITION, DESCRIPTION);

        service.book(givenHotelRoomBookingDto());

        thenBookingShouldBeCreated();
    }

    @Test
    void shouldPublishHotelRoomBookedEvent() {
        ArgumentCaptor<HotelRoomBooked> captor = ArgumentCaptor.forClass(HotelRoomBooked.class);
        Hotel hotel = givenExistingHotel();
        hotel.addRoom(ROOM_NUMBER, SPACES_DEFINITION, DESCRIPTION);

        service.book(givenHotelRoomBookingDto());

        then(eventChannel).should().publish(captor.capture());
        HotelRoomBooked actual = captor.getValue();
        Assertions.assertThat(actual.getEventId()).isEqualTo(FakeEventIdFactory.UUID);
        Assertions.assertThat(actual.getEventCreationDateTime()).isEqualTo(FakeClock.NOW);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    private HotelRoomBookingDto givenHotelRoomBookingDto() {
        return new HotelRoomBookingDto(HOTEL_ID, ROOM_NUMBER, TENANT_ID, DAYS);
    }

    private void thenBookingShouldBeCreated() {
        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        BDDMockito.then(bookingRepository).should().save(captor.capture());

        BookingAssertion.assertThat(captor.getValue())
                .isHotelRoom()
                .hasTenantIdEqualTo(TENANT_ID)
                .containsAllDays(DAYS);
    }

    private HotelRoom createHotelRoom() {
        return hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(ROOM_NUMBER)
                .withSpacesDefinition(SPACES_DEFINITION)
                .withDescription(DESCRIPTION)
                .build();
    }

    private Hotel givenExistingHotel() {
        Hotel hotel = hotel()
                .withName(NAME)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withBuildingNumber(BUILDING_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();
        given(hotelRepository.findById(HOTEL_ID)).willReturn(hotel);

        return hotel;
    }
}