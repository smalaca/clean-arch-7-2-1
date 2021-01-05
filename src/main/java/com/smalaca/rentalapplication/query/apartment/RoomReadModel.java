package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Embeddable;

@Embeddable
public class RoomReadModel {
    private String name;
    private Double size;

    private RoomReadModel() {}

    public String getName() {
        return name;
    }

    public Double getSize() {
        return size;
    }
}
