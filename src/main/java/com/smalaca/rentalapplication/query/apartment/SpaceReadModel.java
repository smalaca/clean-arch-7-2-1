package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.Embeddable;

@Embeddable
public class SpaceReadModel {
    private String name;
    private Double size;

    private SpaceReadModel() {}

    public String getName() {
        return name;
    }

    public Double getSize() {
        return size;
    }
}
