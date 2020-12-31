package com.smalaca.rentalapplication.query.apartment;

public class ApartmentDetails {
    private static final ApartmentReadModel NO_APARTMENT = null;
    private static final ApartmentBookingHistoryReadModel NO_HISTORY = null;

    private final ApartmentReadModel apartment;
    private final ApartmentBookingHistoryReadModel bookingHistory;

    ApartmentDetails(ApartmentReadModel apartment, ApartmentBookingHistoryReadModel bookingHistory) {
        this.apartment = apartment;
        this.bookingHistory = bookingHistory;
    }

    static ApartmentDetails notExisting() {
        return new ApartmentDetails(NO_APARTMENT, NO_HISTORY);
    }

    public ApartmentReadModel getApartment() {
        return apartment;
    }

    public ApartmentBookingHistoryReadModel getBookingHistory() {
        return bookingHistory;
    }
}
