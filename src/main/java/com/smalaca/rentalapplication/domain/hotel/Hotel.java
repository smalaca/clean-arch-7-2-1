package com.smalaca.rentalapplication.domain.hotel;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @GeneratedValue
    private String id;

    private final String name;

    @Embedded
    private final Address address;

    Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
