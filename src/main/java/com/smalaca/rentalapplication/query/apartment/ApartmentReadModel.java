package com.smalaca.rentalapplication.query.apartment;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class ApartmentReadModel {
    @Id
    @GeneratedValue
    private UUID id;

    private String ownerId;
    private String street;
    private String postalCode;
    @Column(name = "house_number")
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String country;
    private String description;

    @ElementCollection
    @CollectionTable(name = "APARTMENT_ROOM", joinColumns = @JoinColumn(name = "APARTMENT_ID"))
    private List<RoomReadModel> rooms;

    private ApartmentReadModel() {}

    public String getId() {
        return id.toString();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public List<RoomReadModel> getRooms() {
        return rooms;
    }
}
