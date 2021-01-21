package com.smalaca.payment.domain.payment;

import com.smalaca.payment.domain.events.PaymentEventChannel;
import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;

import java.math.BigDecimal;

public class PaymentEventsPublisher {
    private final PaymentEventChannel eventChannel;
    private final EventIdFactory eventIdFactory;
    private final Clock clock;

    public PaymentEventsPublisher(PaymentEventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        this.eventChannel = eventChannel;
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
    }

    void paymentCompleted(String senderId, String recipientId, BigDecimal totalAmount) {
        PaymentCompleted event = new PaymentCompleted(eventIdFactory.create(), clock.now(), senderId, recipientId, totalAmount);
        eventChannel.publish(event);
    }

    void paymentFailed(String senderId, String recipientId, BigDecimal totalAmount) {
        PaymentFailed event = new PaymentFailed(eventIdFactory.create(), clock.now(), senderId, recipientId, totalAmount);
        eventChannel.publish(event);
    }
}
