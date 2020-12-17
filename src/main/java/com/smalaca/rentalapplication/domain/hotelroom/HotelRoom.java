package com.smalaca.rentalapplication.domain.hotelroom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class HotelRoom {
    @Id
    @GeneratedValue
    private String hotelRoomId;
    private final String hotelId;
    private final int number;

    @OneToMany
    private final List<Space> spaces;

    private final String description;

    HotelRoom(String hotelId, int number, List<Space> spaces, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spaces = spaces;
        this.description = description;
    }
}
