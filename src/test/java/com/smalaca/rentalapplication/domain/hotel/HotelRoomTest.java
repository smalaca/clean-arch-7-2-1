package com.smalaca.rentalapplication.domain.hotel;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class HotelRoomTest {
    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final int ROOM_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";
    private static final String TENANT_ID = "325426";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private final HotelRoomEventsPublisher hotelRoomEventsPublisher = mock(HotelRoomEventsPublisher.class);

    @Test
    void shouldCreateHotelRoomWithAllRequiredInformation() {
        HotelRoom actual = hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(ROOM_NUMBER)
                .withSpacesDefinition(SPACES_DEFINITION)
                .withDescription(DESCRIPTION)
                .build();

        HotelRoomAssertion.assertThat(actual)
                .hasHotelIdEqualTo(HOTEL_ID)
                .hasRoomNumberEqualTo(ROOM_NUMBER)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                .hasDescriptionEqualTo(DESCRIPTION);
    }

    @Test
    void shouldCreateBookingOnceBooked() {
        HotelRoom hotelRoom = givenHotelRoom();

        Booking actual = hotelRoom.book(TENANT_ID, DAYS, hotelRoomEventsPublisher);

        BookingAssertion.assertThat(actual)
                .isHotelRoom()
                .hasTenantIdEqualTo(TENANT_ID)
                .containsAllDays(DAYS);
    }

    @Test
    void shouldPublishHotelRoomBooked() {
        HotelRoom hotelRoom = givenHotelRoom();

        hotelRoom.book(TENANT_ID, DAYS, hotelRoomEventsPublisher);

        BDDMockito.then(hotelRoomEventsPublisher).should().publishHotelRoomBooked(any(), eq(HOTEL_ID), eq(TENANT_ID), eq(DAYS));
    }

    private HotelRoom givenHotelRoom() {
        return hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(ROOM_NUMBER)
                .withSpacesDefinition(SPACES_DEFINITION)
                .withDescription(DESCRIPTION)
                .build();
    }
}