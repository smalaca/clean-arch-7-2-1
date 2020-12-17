package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.hotel.Address;
import com.smalaca.rentalapplication.domain.hotel.Hotel;

public class HotelApplicationService {
    public void add(String name, String street, String postalCode, String buildingNumber, String city, String country) {
        Address address = new Address(street, postalCode, buildingNumber, city, country);
        Hotel hotel = new Hotel(name, address);
    }
}
