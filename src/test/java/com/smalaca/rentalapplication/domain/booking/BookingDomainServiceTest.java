package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

class BookingDomainServiceTest {
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID = "5678";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));

    private final EventIdFactory eventIdFactory = mock(EventIdFactory.class);
    private final Clock clock = mock(Clock.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingDomainService service = new BookingDomainServiceFactory().create(eventIdFactory, clock, eventChannel);

    @Test
    void shouldAcceptBookingWhenNoOtherBookingsFound() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);

        service.accept(booking, emptyList());

        BookingAssertion.assertThat(booking).isAccepted();
    }
}