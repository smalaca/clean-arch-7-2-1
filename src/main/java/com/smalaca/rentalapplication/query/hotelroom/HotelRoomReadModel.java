package com.smalaca.rentalapplication.query.hotelroom;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
    @Id
    @GeneratedValue
    private UUID hotelRoomId;
    private String hotelId;
    private int number;

    @ElementCollection
    private List<SpaceReadModel> spaces;

    private String description;

    private HotelRoomReadModel() {}

    public HotelRoomReadModel(String hotelId, int number, List<SpaceReadModel> spaces, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spaces = spaces;
        this.description = description;
    }

    public String getHotelRoomId() {
        return hotelRoomId.toString();
    }

    public String getHotelId() {
        return hotelId;
    }

    public int getNumber() {
        return number;
    }

    public List<SpaceReadModel> getSpaces() {
        return spaces;
    }

    public String getDescription() {
        return description;
    }
}
