package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomReadModel {
    private String name;
    @Column(name = "size")
    private Double size;

    private RoomReadModel() {}

    public String getName() {
        return name;
    }

    public Double getSize() {
        return size;
    }
}
