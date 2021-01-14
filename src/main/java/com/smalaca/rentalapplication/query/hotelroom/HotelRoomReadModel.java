package com.smalaca.rentalapplication.query.hotelroom;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "HOTEL_ID")
    private UUID hotelId;
    private int number;

    @ElementCollection
    @CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
    private List<SpaceReadModel> spaces;

    private String description;

    private HotelRoomReadModel() {}

    public String getId() {
        return id.toString();
    }

    public String getHotelId() {
        return hotelId.toString();
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
