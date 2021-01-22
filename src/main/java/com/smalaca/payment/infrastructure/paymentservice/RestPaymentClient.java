package com.smalaca.payment.infrastructure.paymentservice;

import com.smalaca.payment.domain.payment.PaymentService;
import com.smalaca.payment.domain.payment.PaymentStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

class RestPaymentClient implements PaymentService {
    private final RestTemplate restTemplate;
    private final String url;

    RestPaymentClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public PaymentStatus transfer(String senderId, String recipientId, BigDecimal amount) {
        PaymentRequest request = new PaymentRequest(senderId, recipientId, amount);
        PaymentResponse response = restTemplate.postForObject(url + "/payment", request, PaymentResponse.class);

        return response.paymentStatus();
    }
}
