package com.smalaca.rentalapplication.query.hotelroom;

import javax.persistence.Embeddable;

@Embeddable
public class SpaceReadModel {
    private String name;
    private Double value;

    private SpaceReadModel() {}

    public SpaceReadModel(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
