package com.smalaca.rentalapplication.infrastructure.rest.api.client;

public class RentalApplicationClientException extends RuntimeException {
    RentalApplicationClientException(Exception exception) {
        super(exception);
    }
}
