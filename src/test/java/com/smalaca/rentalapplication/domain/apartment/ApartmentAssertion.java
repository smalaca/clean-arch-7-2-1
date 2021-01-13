package com.smalaca.rentalapplication.domain.apartment;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ApartmentAssertion {
    private final Apartment actual;

    private ApartmentAssertion(Apartment actual) {
        this.actual = actual;
    }

    public static ApartmentAssertion assertThat(Apartment actual) {
        return new ApartmentAssertion(actual);
    }


    public ApartmentAssertion hasOwnerIdEqualsTo(String ownerId) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", ownerId);
        return this;
    }

    public ApartmentAssertion hasDescriptionEqualsTo(String description) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", description);
        return this;
    }

    public ApartmentAssertion hasAddressEqualsTo(
            String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("apartmentNumber", apartmentNumber);

        Assertions.assertThat(actual).extracting("address")
                .hasFieldOrPropertyWithValue("street", street)
                .hasFieldOrPropertyWithValue("postalCode", postalCode)
                .hasFieldOrPropertyWithValue("buildingNumber", houseNumber)
                .hasFieldOrPropertyWithValue("city", city)
                .hasFieldOrPropertyWithValue("country", country);

        return this;
    }

    public ApartmentAssertion hasSpacesEqualsTo(Map<String, Double> spacesDefinition) {
        Assertions.assertThat(actual).extracting("spaces").satisfies(roomsActual -> {
            List<Room> rooms = (List<Room>) roomsActual;
            Assertions.assertThat(rooms).hasSize(spacesDefinition.size());

            spacesDefinition.forEach((name, squareMeter) -> {
                Assertions.assertThat(rooms).anySatisfy(hasSpaceThat(name, squareMeter));
            });
        });

        return this;
    }

    private Consumer<Room> hasSpaceThat(String name, Double squareMeter) {
        return room -> Assertions.assertThat(room)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
    }
}
