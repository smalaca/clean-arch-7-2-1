package com.smalaca.rentalapplication.application.hotelroom;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.apartment.Booking;
import com.smalaca.rentalapplication.domain.apartment.BookingAssertion;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoom;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomFactory;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

class HotelRoomApplicationServiceTest {
    private static final String TENANT_ID = "4321";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));

    private final HotelRoomRepository hotelRoomRepository = Mockito.mock(HotelRoomRepository.class);
    private final BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
    private final EventChannel eventChannel = Mockito.mock(EventChannel.class);
    private final HotelRoomApplicationService service = new HotelRoomApplicationService(hotelRoomRepository, bookingRepository, eventChannel);

    @Test
    void shouldCreateBookingWhenHotelRoomBooked() {
        String hotelRoomId = "1234";
        givenHotelRoom(hotelRoomId);

        service.book(hotelRoomId, TENANT_ID, DAYS);

        thenBookingShouldBeCreated();
    }

    private void thenBookingShouldBeCreated() {
        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        BDDMockito.then(bookingRepository).should().save(captor.capture());

        BookingAssertion.assertThat(captor.getValue())
                .isHotelRoom()
                .hasTenantIdEqualTo(TENANT_ID)
                .containsAllDays(DAYS);
    }

    private void givenHotelRoom(String hotelRoomId) {
        HotelRoom hotelRoom = createHotelRoom();
        BDDMockito.given(hotelRoomRepository.findById(hotelRoomId)).willReturn(hotelRoom);
    }

    private HotelRoom createHotelRoom() {
        return new HotelRoomFactory().create("5678", 42, ImmutableMap.of("Room1", 30.0), "This is very nice place");
    }
}