package com.smalaca.rentalapplication.query.apartment;

public class ApartmentDetails {
    private final ApartmentReadModel apartment;
    private final ApartmentBookingHistoryReadModel bookingHistory;

    ApartmentDetails(ApartmentReadModel apartment, ApartmentBookingHistoryReadModel bookingHistory) {
        this.apartment = apartment;
        this.bookingHistory = bookingHistory;
    }

    public ApartmentReadModel getApartment() {
        return apartment;
    }

    public ApartmentBookingHistoryReadModel getBookingHistory() {
        return bookingHistory;
    }
}
