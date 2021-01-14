package com.smalaca.rentalapplication.domain.space;

public class NotEnoughSpacesGivenException extends RuntimeException {
    private NotEnoughSpacesGivenException(String message) {
        super(message);
    }

    static RuntimeException noSpaces() {
        return new NotEnoughSpacesGivenException("No spaces given.");
    }
}
