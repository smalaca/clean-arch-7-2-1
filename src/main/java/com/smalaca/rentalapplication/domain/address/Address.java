package com.smalaca.rentalapplication.domain.address;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class Address {
    private String street;
    private String postalCode;
    private String buildingNumber;
    private String city;
    private String country;

    private Address() {}

    public Address(String street, String postalCode, String buildingNumber, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(street, address.street)
                .append(postalCode, address.postalCode)
                .append(buildingNumber, address.buildingNumber)
                .append(city, address.city)
                .append(country, address.country)
                .isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(street)
                .append(postalCode)
                .append(buildingNumber)
                .append(city)
                .append(country)
                .toHashCode();
    }
}
