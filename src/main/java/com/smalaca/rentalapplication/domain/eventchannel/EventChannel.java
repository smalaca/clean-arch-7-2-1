package com.smalaca.rentalapplication.domain.eventchannel;

import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);
}
