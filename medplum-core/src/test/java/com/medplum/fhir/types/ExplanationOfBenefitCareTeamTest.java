/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class ExplanationOfBenefitCareTeamTest {

    @Test
    public void testConstructor() {
        assertNotNull(new ExplanationOfBenefit.ExplanationOfBenefitCareTeam(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testId() {
        assertEquals("x", ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().id("x").build().id());
    }

    @Test
    public void testModifierExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().modifierExtension(value).build().modifierExtension());
    }

    @Test
    public void testSequence() {
        assertEquals(1, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().sequence(1).build().sequence());
    }

    @Test
    public void testProvider() {
        final Reference value = Reference.create().build();
        assertEquals(value, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().provider(value).build().provider());
    }

    @Test
    public void testResponsible() {
        assertEquals(true, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().responsible(true).build().responsible());
    }

    @Test
    public void testRole() {
        final CodeableConcept value = CodeableConcept.create().build();
        assertEquals(value, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().role(value).build().role());
    }

    @Test
    public void testQualification() {
        final CodeableConcept value = CodeableConcept.create().build();
        assertEquals(value, ExplanationOfBenefit.ExplanationOfBenefitCareTeam.create().qualification(value).build().qualification());
    }
}