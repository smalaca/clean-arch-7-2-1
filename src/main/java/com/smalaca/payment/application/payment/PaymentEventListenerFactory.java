package com.smalaca.payment.application.payment;

import com.smalaca.payment.domain.events.PaymentEventChannel;
import com.smalaca.payment.domain.payment.PaymentEventsPublisher;
import com.smalaca.payment.domain.payment.PaymentFactory;
import com.smalaca.payment.domain.payment.PaymentService;
import com.smalaca.sharedkernel.domain.clock.Clock;
import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentEventListenerFactory {
    @Bean
    PaymentEventListener paymentEventListener(PaymentService paymentService, PaymentEventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        PaymentEventsPublisher paymentEventsPublisher = new PaymentEventsPublisher(eventChannel, eventIdFactory, clock);
        PaymentFactory paymentFactory = new PaymentFactory(paymentService, paymentEventsPublisher);
        return new PaymentEventListener(paymentFactory);
    }
}
