package com.smalaca.payment.domain.payment;

import com.google.common.collect.ImmutableMap;
import com.smalaca.payment.domain.events.PaymentEventChannel;
import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;

import java.math.BigDecimal;
import java.util.Map;

import static com.smalaca.payment.domain.payment.PaymentStatus.NOT_ENOUGH_MONEY;
import static com.smalaca.payment.domain.payment.PaymentStatus.SUCCESS;

public class PaymentEventsPublisher {
    private final Map<PaymentStatus, EventPublisher> eventsPublisher = ImmutableMap.of(
            SUCCESS, this::paymentCompleted,
            NOT_ENOUGH_MONEY, this::paymentFailed
    );

    private final PaymentEventChannel eventChannel;
    private final EventIdFactory eventIdFactory;
    private final Clock clock;

    public PaymentEventsPublisher(PaymentEventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        this.eventChannel = eventChannel;
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
    }

    void publish(PaymentStatus paymentStatus, String senderId, String recipientId, BigDecimal totalAmount) {
        eventsPublisher.get(paymentStatus).publish(senderId, recipientId, totalAmount);
    }

    private void paymentCompleted(String senderId, String recipientId, BigDecimal totalAmount) {
        PaymentCompleted event = new PaymentCompleted(eventIdFactory.create(), clock.now(), senderId, recipientId, totalAmount);
        eventChannel.publish(event);
    }

    private void paymentFailed(String senderId, String recipientId, BigDecimal totalAmount) {
        PaymentFailed event = new PaymentFailed(eventIdFactory.create(), clock.now(), senderId, recipientId, totalAmount);
        eventChannel.publish(event);
    }

    private interface EventPublisher {
        void publish(String senderId, String recipientId, BigDecimal totalAmount);
    }
}
