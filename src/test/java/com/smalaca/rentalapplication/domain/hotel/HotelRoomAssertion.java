package com.smalaca.rentalapplication.domain.hotel;

import com.smalaca.rentalapplication.domain.space.Space;
import com.smalaca.rentalapplication.domain.space.SpacesAssertion;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

public class HotelRoomAssertion {
    private final HotelRoom actual;

    private HotelRoomAssertion(HotelRoom actual) {
        this.actual = actual;
    }

    public static HotelRoomAssertion assertThat(HotelRoom actual) {
        return new HotelRoomAssertion(actual);
    }

    public HotelRoomAssertion hasSpacesDefinitionEqualTo(Map<String, Double> expected) {
        Assertions.assertThat(actual).extracting("spaces").satisfies(spacesActual -> {
            SpacesAssertion.assertThat((List<Space>) spacesActual)
                    .hasSize(expected.size())
                    .hasAllSpacesFrom(expected);
        });

        return this;
    }

    public HotelRoomAssertion hasDescriptionEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", expected);
        return this;
    }

    public HotelRoomAssertion isEqualTo(HotelRoomRequirements requirements) {
        Assertions.assertThat(actual).isEqualTo(requirements.get());
        return this;
    }
}