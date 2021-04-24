/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class SubstanceAmountReferenceRangeTest {

    @Test
    public void testConstructor() {
        assertNotNull(new SubstanceAmount.SubstanceAmountReferenceRange(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(SubstanceAmount.SubstanceAmountReferenceRange.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testId() {
        assertEquals("x", SubstanceAmount.SubstanceAmountReferenceRange.create().id("x").build().id());
    }

    @Test
    public void testModifierExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, SubstanceAmount.SubstanceAmountReferenceRange.create().modifierExtension(value).build().modifierExtension());
    }

    @Test
    public void testLowLimit() {
        final Quantity value = Quantity.create().build();
        assertEquals(value, SubstanceAmount.SubstanceAmountReferenceRange.create().lowLimit(value).build().lowLimit());
    }

    @Test
    public void testHighLimit() {
        final Quantity value = Quantity.create().build();
        assertEquals(value, SubstanceAmount.SubstanceAmountReferenceRange.create().highLimit(value).build().highLimit());
    }
}