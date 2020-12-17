package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Address;
import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.Room;
import com.smalaca.rentalapplication.domain.apartment.SquareMeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartmentApplicationService {
    public void add(
            String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
            String city, String country, String description, Map<String, Double> roomsDefinition) {
        
        Address address = new Address(street, postalCode, houseNumber, apartmentNumber, city, country);
        List<Room> rooms = new ArrayList<>();
        roomsDefinition.forEach((name, size) -> {
            SquareMeter squareMeter = new SquareMeter(size);
            rooms.add(new Room(name, squareMeter));
        });

        Apartment apartment = new Apartment(ownerId, address, description);
    }
}
