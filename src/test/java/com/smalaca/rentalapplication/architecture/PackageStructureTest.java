package com.smalaca.rentalapplication.architecture;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

class PackageStructureTest {
    @Test
    void domainShouldTalkOnlyWithDomain() {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..domain..")
                .should().onlyAccessClassesThat().resideInAnyPackage("..domain..", "java..")
                .check(RentalApplicationClasses.get());
    }
}
