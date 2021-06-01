/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class DataRequirementCodeFilterTest {

    @Test
    public void testConstructor() {
        assertNotNull(new DataRequirement.DataRequirementCodeFilter(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(DataRequirement.DataRequirementCodeFilter.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testCopyAll() {
        final DataRequirement.DataRequirementCodeFilter x = DataRequirement.DataRequirementCodeFilter.create().build();
        final DataRequirement.DataRequirementCodeFilter y = DataRequirement.DataRequirementCodeFilter.create().copyAll(x).build();
        assertEquals(x, y);
    }

    @Test
    public void testId() {
        assertEquals("x", DataRequirement.DataRequirementCodeFilter.create().id("x").build().id());
    }

    @Test
    public void testExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, DataRequirement.DataRequirementCodeFilter.create().extension(value).build().extension());
    }

    @Test
    public void testModifierExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, DataRequirement.DataRequirementCodeFilter.create().modifierExtension(value).build().modifierExtension());
    }

    @Test
    public void testPath() {
        assertEquals("x", DataRequirement.DataRequirementCodeFilter.create().path("x").build().path());
    }

    @Test
    public void testSearchParam() {
        assertEquals("x", DataRequirement.DataRequirementCodeFilter.create().searchParam("x").build().searchParam());
    }

    @Test
    public void testValueSet() {
        assertEquals("x", DataRequirement.DataRequirementCodeFilter.create().valueSet("x").build().valueSet());
    }

    @Test
    public void testCode() {
        final java.util.List<Coding> value = java.util.Collections.emptyList();
        assertEquals(value, DataRequirement.DataRequirementCodeFilter.create().code(value).build().code());
    }
}