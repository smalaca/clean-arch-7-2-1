package com.smalaca.payment.application.payment;

import com.smalaca.payment.domain.events.PaymentEventChannel;
import com.smalaca.payment.domain.payment.PaymentCompleted;
import com.smalaca.payment.domain.payment.PaymentCompletedAssertion;
import com.smalaca.payment.domain.payment.PaymentFailed;
import com.smalaca.payment.domain.payment.PaymentFailedAssertion;
import com.smalaca.payment.domain.payment.PaymentService;
import com.smalaca.payment.domain.payment.PaymentStatus;
import com.smalaca.rentalapplication.domain.agreement.AgreementAccepted;
import com.smalaca.rentalapplication.domain.agreement.AgreementAcceptedTestFactory;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.smalaca.rentalapplication.domain.rentalplace.RentalType.APARTMENT;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class PaymentEventListenerTest {
    private static final String RENTAL_TYPE = APARTMENT.name();
    private static final String RENTAL_PLACE_ID = "5748";
    private static final String OWNER_ID = "4346";
    private static final String TENANT_ID = "1234";
    public static final BigDecimal PRICE = BigDecimal.valueOf(100.00);
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(200.00);
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String EVENT_ID = "213213";
    private static final LocalDateTime CREATION_TIME = LocalDateTime.now();

    private final PaymentService paymentService = mock(PaymentService.class);
    private final PaymentEventChannel eventChannel = mock(PaymentEventChannel.class);
    private final PaymentEventListener paymentEventListener = new PaymentEventListenerFactory()
            .paymentEventListener(paymentService, eventChannel, new FakeEventIdFactory(), new FakeClock());

    @Test
    void shouldPay() {
        givenSuccessfulPayment();

        paymentEventListener.consume(givenAgreementAccepted());

        then(paymentService).should().transfer(TENANT_ID, OWNER_ID, AMOUNT);
    }

    @Test
    void shouldPublishEventWhenSuccessfullyPay() {
        givenSuccessfulPayment();
        ArgumentCaptor<PaymentCompleted> captor = ArgumentCaptor.forClass(PaymentCompleted.class);

        paymentEventListener.consume(givenAgreementAccepted());

        then(eventChannel).should().publish(captor.capture());
        PaymentCompletedAssertion.assertThat(captor.getValue())
                .hasSenderId(TENANT_ID)
                .hasRecipientId(OWNER_ID)
                .hasAmount(AMOUNT);
    }

    private void givenSuccessfulPayment() {
        given(paymentService.transfer(TENANT_ID, OWNER_ID, AMOUNT)).willReturn(PaymentStatus.SUCCESS);
    }

    @Test
    void shouldRecognizeThereIsNotEnoughMoney() {
        givenNotEnoughMoney();
        ArgumentCaptor<PaymentFailed> captor = ArgumentCaptor.forClass(PaymentFailed.class);

        paymentEventListener.consume(givenAgreementAccepted());

        then(eventChannel).should().publish(captor.capture());
        PaymentFailedAssertion.assertThat(captor.getValue())
                .hasSenderId(TENANT_ID)
                .hasRecipientId(OWNER_ID)
                .hasAmount(AMOUNT);
    }

    private void givenNotEnoughMoney() {
        given(paymentService.transfer(TENANT_ID, OWNER_ID, AMOUNT)).willReturn(PaymentStatus.NOT_ENOUGH_MONEY);
    }

    private AgreementAccepted givenAgreementAccepted() {
        return AgreementAcceptedTestFactory.create(
                EVENT_ID, CREATION_TIME, RENTAL_TYPE, RENTAL_PLACE_ID, OWNER_ID, TENANT_ID, DAYS, PRICE);
    }
}