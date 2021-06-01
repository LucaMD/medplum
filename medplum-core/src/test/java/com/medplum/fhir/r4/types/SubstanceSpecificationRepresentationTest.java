/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class SubstanceSpecificationRepresentationTest {

    @Test
    public void testConstructor() {
        assertNotNull(new SubstanceSpecification.SubstanceSpecificationRepresentation(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(SubstanceSpecification.SubstanceSpecificationRepresentation.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testCopyAll() {
        final SubstanceSpecification.SubstanceSpecificationRepresentation x = SubstanceSpecification.SubstanceSpecificationRepresentation.create().build();
    final SubstanceSpecification.SubstanceSpecificationRepresentation y =
            SubstanceSpecification.SubstanceSpecificationRepresentation.create().copyAll(x).build();
        assertEquals(x, y);
    }

    @Test
    public void testId() {
        assertEquals("x", SubstanceSpecification.SubstanceSpecificationRepresentation.create().id("x").build().id());
    }

    @Test
    public void testExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, SubstanceSpecification.SubstanceSpecificationRepresentation.create().extension(value).build().extension());
    }

    @Test
    public void testModifierExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, SubstanceSpecification.SubstanceSpecificationRepresentation.create().modifierExtension(value).build().modifierExtension());
    }

    @Test
    public void testType() {
        final CodeableConcept value = CodeableConcept.create().build();
        assertEquals(value, SubstanceSpecification.SubstanceSpecificationRepresentation.create().type(value).build().type());
    }

    @Test
    public void testRepresentation() {
        assertEquals("x", SubstanceSpecification.SubstanceSpecificationRepresentation.create().representation("x").build().representation());
    }

    @Test
    public void testAttachment() {
        final Attachment value = Attachment.create().build();
        assertEquals(value, SubstanceSpecification.SubstanceSpecificationRepresentation.create().attachment(value).build().attachment());
    }
}