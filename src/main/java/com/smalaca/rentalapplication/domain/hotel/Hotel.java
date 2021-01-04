package com.smalaca.rentalapplication.domain.hotel;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Embedded
    private Address address;

    private Hotel() {}

    Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String id() {
        return id.toString();
    }
}
