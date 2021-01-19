package com.smalaca.rentalapplication.domain.apartment;

public class OwnerDoesNotExistException extends RuntimeException {
    OwnerDoesNotExistException(String ownerId) {
        super("Owner with id " + ownerId + " does not exist.");
    }
}
