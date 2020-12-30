package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Embeddable;

@Embeddable
public class RoomReadModel {
    private String name;
    private Double size;

    private RoomReadModel() {}

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
