package com.smalaca.rentalapplication.application.booking;

public class BookingReject {
    private final String bookingId;

    public BookingReject(String bookingId) {
        this.bookingId = bookingId;
    }

    String getBookingId() {
        return bookingId;
    }
}
