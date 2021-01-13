package com.smalaca.rentalapplication.domain.space;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SpacesAssertion {
    private final List<Space> actual;

    private SpacesAssertion(List<Space> actual) {
        this.actual = actual;
    }

    public static SpacesAssertion assertThat(List<Space> actual) {
        return new SpacesAssertion(actual);
    }

    public SpacesAssertion hasSize(int expected) {
        Assertions.assertThat(actual).hasSize(expected);
        return this;
    }

    public SpacesAssertion hasAllSpacesFrom(Map<String, Double> expected) {
        expected.forEach((name, squareMeter) -> {
            Assertions.assertThat(actual).anySatisfy(hasSpaceThat(name, squareMeter));
        });

        return this;
    }

    private Consumer<Space> hasSpaceThat(String name, Double squareMeter) {
        return space -> Assertions.assertThat(space)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("squareMeter.value", squareMeter);
    }
}
