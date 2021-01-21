package com.smalaca.rentalapplication.domain.eventchannel;

import com.smalaca.rentalapplication.domain.agreement.AgreementAccepted;
import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.booking.BookingRejected;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomBooked;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);

    void publish(HotelRoomBooked hotelRoomBooked);

    void publish(BookingAccepted bookingAccepted);

    void publish(BookingRejected bookingRejected);

    void publish(AgreementAccepted agreementAccepted);
}
