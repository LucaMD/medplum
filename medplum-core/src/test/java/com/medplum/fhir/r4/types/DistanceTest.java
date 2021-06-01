/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class DistanceTest {

    @Test
    public void testConstructor() {
        assertNotNull(new Distance(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(Distance.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testCopyAll() {
        final Distance x = Distance.create().build();
        final Distance y = Distance.create().copyAll(x).build();
        assertEquals(x, y);
    }

    @Test
    public void testId() {
        assertEquals("x", Distance.create().id("x").build().id());
    }

    @Test
    public void testExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, Distance.create().extension(value).build().extension());
    }

    @Test
    public void testValue() {
        assertEquals(1.0, Distance.create().value(1.0).build().value());
    }

    @Test
    public void testComparator() {
        assertEquals("x", Distance.create().comparator("x").build().comparator());
    }

    @Test
    public void testUnit() {
        assertEquals("x", Distance.create().unit("x").build().unit());
    }

    @Test
    public void testSystem() {
        final java.net.URI value = java.net.URI.create("https://www.example.com");
        assertEquals(value, Distance.create().system(value).build().system());
    }

    @Test
    public void testCode() {
        assertEquals("x", Distance.create().code("x").build().code());
    }
}