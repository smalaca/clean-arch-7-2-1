package com.smalaca.rentalapplication.application.agreement;

import com.smalaca.rentalapplication.domain.agreement.AgreementEventsPublisher;
import com.smalaca.rentalapplication.domain.agreement.AgreementRepository;
import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;

public class AgreementApplicationServiceFactor {
    AgreementApplicationService agreementApplicationService(
            AgreementRepository agreementRepository, EventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        return new AgreementApplicationService(agreementRepository, new AgreementEventsPublisher(eventChannel, eventIdFactory, clock));
    }
}
