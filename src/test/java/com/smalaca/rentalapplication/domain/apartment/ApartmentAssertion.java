package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.space.Space;
import com.smalaca.rentalapplication.domain.space.SpacesAssertion;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

public class ApartmentAssertion {
    private final Apartment actual;

    private ApartmentAssertion(Apartment actual) {
        this.actual = actual;
    }

    public static ApartmentAssertion assertThat(Apartment actual) {
        return new ApartmentAssertion(actual);
    }


    public ApartmentAssertion hasDescriptionEqualsTo(String description) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", description);
        return this;
    }

    public ApartmentAssertion hasSpacesEqualsTo(Map<String, Double> expected) {
        Assertions.assertThat(actual).extracting("spaces").satisfies(roomsActual -> {
            SpacesAssertion.assertThat((List<Space>) roomsActual)
                    .hasSize(expected.size())
                    .hasAllSpacesFrom(expected);
        });

        return this;
    }

    public ApartmentAssertion isEqualTo(ApartmentRequirements requirements) {
        Assertions.assertThat(actual).isEqualTo(requirements.get());
        return this;
    }
}
