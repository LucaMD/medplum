/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.json.Json;

import org.junit.Test;

public class MessageHeaderDestinationTest {

    @Test
    public void testConstructor() {
        assertNotNull(new MessageHeader.MessageHeaderDestination(Json.createObjectBuilder().build()));
    }

    @Test
    public void testBuilderFromJsonObject() {
        assertNotNull(MessageHeader.MessageHeaderDestination.create(Json.createObjectBuilder().build()).build());
    }

    @Test
    public void testId() {
        assertEquals("x", MessageHeader.MessageHeaderDestination.create().id("x").build().id());
    }

    @Test
    public void testModifierExtension() {
        final java.util.List<Extension> value = java.util.Collections.emptyList();
        assertEquals(value, MessageHeader.MessageHeaderDestination.create().modifierExtension(value).build().modifierExtension());
    }

    @Test
    public void testName() {
        assertEquals("x", MessageHeader.MessageHeaderDestination.create().name("x").build().name());
    }

    @Test
    public void testTarget() {
        final Reference value = Reference.create().build();
        assertEquals(value, MessageHeader.MessageHeaderDestination.create().target(value).build().target());
    }

    @Test
    public void testEndpoint() {
        assertEquals("x", MessageHeader.MessageHeaderDestination.create().endpoint("x").build().endpoint());
    }

    @Test
    public void testReceiver() {
        final Reference value = Reference.create().build();
        assertEquals(value, MessageHeader.MessageHeaderDestination.create().receiver(value).build().receiver());
    }
}