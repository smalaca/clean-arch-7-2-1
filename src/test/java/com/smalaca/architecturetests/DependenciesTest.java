package com.smalaca.architecturetests;

import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ArchitectureTest")
class DependenciesTest {
    @Test
    void shouldHaveNoCycles() {
        SlicesRuleDefinition.slices()
                .matching("com.smalaca.rentalapplication(*)..")
                .should().beFreeOfCycles();
    }
}
