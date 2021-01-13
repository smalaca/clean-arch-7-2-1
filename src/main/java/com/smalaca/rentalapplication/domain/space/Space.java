package com.smalaca.rentalapplication.domain.space;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class Space {
    private String name;

    @Embedded
    private SquareMeter squareMeter;

    private Space() {}

    Space(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
