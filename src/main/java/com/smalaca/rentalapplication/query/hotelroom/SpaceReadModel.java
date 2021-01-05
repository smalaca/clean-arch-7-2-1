package com.smalaca.rentalapplication.query.hotelroom;

import javax.persistence.Embeddable;

@Embeddable
public class SpaceReadModel {
    private String name;
    private Double value;

    private SpaceReadModel() {}

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
