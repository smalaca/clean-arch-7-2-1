package com.smalaca.rentalapplication.domain.booking;

import com.google.common.collect.ImmutableList;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static com.smalaca.rentalapplication.domain.booking.RentalType.APARTMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class BookingEventsPublisherTest {
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingEventsPublisher publisher = new BookingEventsPublisher(new FakeEventIdFactory(), new FakeClock(), eventChannel);

    @Test
    void shouldPublishBookingAccepted() {
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);
        String rentalPlaceId = "1234";
        String tenantId = "7890";
        List<LocalDate> days = ImmutableList.of(
                LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 11), LocalDate.of(2020, 10, 12));

        publisher.bookingAccepted(APARTMENT, rentalPlaceId, tenantId, days);

        then(eventChannel).should().publish(captor.capture());
        BookingAccepted actual = captor.getValue();
        assertThat(actual.getEventId()).isEqualTo(FakeEventIdFactory.UUID);
        assertThat(actual.getEventCreationDateTime()).isEqualTo(FakeClock.NOW);
        assertThat(actual.getRentalType()).isEqualTo("APARTMENT");
        assertThat(actual.getRentalPlaceId()).isEqualTo(rentalPlaceId);
        assertThat(actual.getTenantId()).isEqualTo(tenantId);
        assertThat(actual.getDays()).containsExactlyElementsOf(days);
    }
}