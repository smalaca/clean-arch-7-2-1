package com.smalaca.rentalapplication.domain.agreement;

import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.rentalplace.RentalType;

import java.time.LocalDate;
import java.util.List;

public class AgreementEventsPublisher {
    private final EventChannel eventChannel;
    private final EventIdFactory eventIdFactory;
    private final Clock clock;

    public AgreementEventsPublisher(EventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        this.eventChannel = eventChannel;
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    void agreementAccepted(RentalType rentalType, String rentalPlaceId, String ownerId, String tenantId, List<LocalDate> days, Money price) {
        AgreementAccepted event = new AgreementAccepted(
                eventIdFactory.create(), clock.now(), rentalType.name(), rentalPlaceId, ownerId, tenantId, days, price.getValue());
        eventChannel.publish(event);
    }
}
