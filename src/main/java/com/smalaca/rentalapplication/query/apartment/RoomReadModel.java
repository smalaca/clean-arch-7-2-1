package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
@Table(name = "APARTMENT_ROOM")
public class RoomReadModel {
    private final String name;
    private final Double size;

    RoomReadModel(String name, Double size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Double getSize() {
        return size;
    }
}
