/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class MessageHeader extends FhirResource {
    public static final String RESOURCE_TYPE = "MessageHeader";
    public static final String PROPERTY_RESOURCE_TYPE = "resourceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_META = "meta";
    public static final String PROPERTY_IMPLICIT_RULES = "implicitRules";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_TEXT = "text";
    public static final String PROPERTY_CONTAINED = "contained";
    public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
    public static final String PROPERTY_EVENT_CODING = "eventCoding";
    public static final String PROPERTY_EVENT_URI = "eventUri";
    public static final String PROPERTY_DESTINATION = "destination";
    public static final String PROPERTY_SENDER = "sender";
    public static final String PROPERTY_ENTERER = "enterer";
    public static final String PROPERTY_AUTHOR = "author";
    public static final String PROPERTY_SOURCE = "source";
    public static final String PROPERTY_RESPONSIBLE = "responsible";
    public static final String PROPERTY_REASON = "reason";
    public static final String PROPERTY_RESPONSE = "response";
    public static final String PROPERTY_FOCUS = "focus";
    public static final String PROPERTY_DEFINITION = "definition";

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(final JsonObject data) {
        return new Builder(data);
    }

    public MessageHeader(final JsonObject data) {
        super(data);
    }

    /**
     * A reference to a set of rules that were followed when the resource was
     * constructed, and which must be understood when processing the content.
     * Often, this is a reference to an implementation guide that defines the
     * special rules along with other profiles etc.
     */
    public String implicitRules() {
        return getString(PROPERTY_IMPLICIT_RULES);
    }

    /**
     * The base language in which the resource is written.
     */
    public String language() {
        return getString(PROPERTY_LANGUAGE);
    }

    /**
     * A human-readable narrative that contains a summary of the resource and
     * can be used to represent the content of the resource to a human. The
     * narrative need not encode all the structured data, but is required to
     * contain sufficient detail to make it "clinically safe" for a human to
     * just read the narrative. Resource definitions may define what content
     * should be represented in the narrative to ensure clinical safety.
     */
    public Narrative text() {
        return getObject(Narrative.class, PROPERTY_TEXT);
    }

    /**
     * These resources do not have an independent existence apart from the
     * resource that contains them - they cannot be identified independently,
     * and nor can they have their own independent transaction scope.
     */
    public java.util.List<FhirResource> contained() {
        return getList(FhirResource.class, PROPERTY_CONTAINED);
    }

    /**
     * May be used to represent additional information that is not part of
     * the basic definition of the resource and that modifies the
     * understanding of the element that contains it and/or the understanding
     * of the containing element's descendants. Usually modifier elements
     * provide negation or qualification. To make the use of extensions safe
     * and manageable, there is a strict set of governance applied to the
     * definition and use of extensions. Though any implementer is allowed to
     * define an extension, there is a set of requirements that SHALL be met
     * as part of the definition of the extension. Applications processing a
     * resource are required to check for modifier extensions.
     *
     * Modifier extensions SHALL NOT change the meaning of any elements on
     * Resource or DomainResource (including cannot change the meaning of
     * modifierExtension itself).
     */
    public java.util.List<Extension> modifierExtension() {
        return getList(Extension.class, PROPERTY_MODIFIER_EXTENSION);
    }

    /**
     * Code that identifies the event this message represents and connects it
     * with its definition. Events defined as part of the FHIR specification
     * have the system value
     * "http://terminology.hl7.org/CodeSystem/message-events".  Alternatively
     * uri to the EventDefinition.
     */
    public Coding eventCoding() {
        return getObject(Coding.class, PROPERTY_EVENT_CODING);
    }

    /**
     * Code that identifies the event this message represents and connects it
     * with its definition. Events defined as part of the FHIR specification
     * have the system value
     * "http://terminology.hl7.org/CodeSystem/message-events".  Alternatively
     * uri to the EventDefinition.
     */
    public String eventUri() {
        return getString(PROPERTY_EVENT_URI);
    }

    /**
     * The destination application which the message is intended for.
     */
    public java.util.List<MessageHeaderDestination> destination() {
        return getList(MessageHeaderDestination.class, PROPERTY_DESTINATION);
    }

    /**
     * Identifies the sending system to allow the use of a trust
     * relationship.
     */
    public Reference sender() {
        return getObject(Reference.class, PROPERTY_SENDER);
    }

    /**
     * The person or device that performed the data entry leading to this
     * message. When there is more than one candidate, pick the most proximal
     * to the message. Can provide other enterers in extensions.
     */
    public Reference enterer() {
        return getObject(Reference.class, PROPERTY_ENTERER);
    }

    /**
     * The logical author of the message - the person or device that decided
     * the described event should happen. When there is more than one
     * candidate, pick the most proximal to the MessageHeader. Can provide
     * other authors in extensions.
     */
    public Reference author() {
        return getObject(Reference.class, PROPERTY_AUTHOR);
    }

    /**
     * The source application from which this message originated.
     */
    public MessageHeaderSource source() {
        return getObject(MessageHeaderSource.class, PROPERTY_SOURCE);
    }

    /**
     * The person or organization that accepts overall responsibility for the
     * contents of the message. The implication is that the message event
     * happened under the policies of the responsible party.
     */
    public Reference responsible() {
        return getObject(Reference.class, PROPERTY_RESPONSIBLE);
    }

    /**
     * Coded indication of the cause for the event - indicates  a reason for
     * the occurrence of the event that is a focus of this message.
     */
    public CodeableConcept reason() {
        return getObject(CodeableConcept.class, PROPERTY_REASON);
    }

    /**
     * Information about the message that this message is a response to. 
     * Only present if this message is a response.
     */
    public MessageHeaderResponse response() {
        return getObject(MessageHeaderResponse.class, PROPERTY_RESPONSE);
    }

    /**
     * The actual data of the message - a reference to the root/focus class
     * of the event.
     */
    public java.util.List<Reference> focus() {
        return getList(Reference.class, PROPERTY_FOCUS);
    }

    /**
     * Permanent link to the MessageDefinition for this message.
     */
    public String definition() {
        return getString(PROPERTY_DEFINITION);
    }

    public static class Builder extends FhirResource.Builder {

        private Builder() {
            super(RESOURCE_TYPE);
        }

        private Builder(final JsonObject data) {
            super(RESOURCE_TYPE, data);
        }

        public Builder resourceType(final String resourceType) {
            b.add(PROPERTY_RESOURCE_TYPE, resourceType);
            return this;
        }

        public Builder id(final String id) {
            b.add(PROPERTY_ID, id);
            return this;
        }

        public Builder meta(final Meta meta) {
            b.add(PROPERTY_META, meta);
            return this;
        }

        public Builder implicitRules(final String implicitRules) {
            b.add(PROPERTY_IMPLICIT_RULES, implicitRules);
            return this;
        }

        public Builder language(final String language) {
            b.add(PROPERTY_LANGUAGE, language);
            return this;
        }

        public Builder text(final Narrative text) {
            b.add(PROPERTY_TEXT, text);
            return this;
        }

        public Builder contained(final java.util.List<FhirResource> contained) {
            b.add(PROPERTY_CONTAINED, FhirObject.toArray(contained));
            return this;
        }

        public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
            b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
            return this;
        }

        public Builder eventCoding(final Coding eventCoding) {
            b.add(PROPERTY_EVENT_CODING, eventCoding);
            return this;
        }

        public Builder eventUri(final String eventUri) {
            b.add(PROPERTY_EVENT_URI, eventUri);
            return this;
        }

        public Builder destination(final java.util.List<MessageHeaderDestination> destination) {
            b.add(PROPERTY_DESTINATION, FhirObject.toArray(destination));
            return this;
        }

        public Builder sender(final Reference sender) {
            b.add(PROPERTY_SENDER, sender);
            return this;
        }

        public Builder enterer(final Reference enterer) {
            b.add(PROPERTY_ENTERER, enterer);
            return this;
        }

        public Builder author(final Reference author) {
            b.add(PROPERTY_AUTHOR, author);
            return this;
        }

        public Builder source(final MessageHeaderSource source) {
            b.add(PROPERTY_SOURCE, source);
            return this;
        }

        public Builder responsible(final Reference responsible) {
            b.add(PROPERTY_RESPONSIBLE, responsible);
            return this;
        }

        public Builder reason(final CodeableConcept reason) {
            b.add(PROPERTY_REASON, reason);
            return this;
        }

        public Builder response(final MessageHeaderResponse response) {
            b.add(PROPERTY_RESPONSE, response);
            return this;
        }

        public Builder focus(final java.util.List<Reference> focus) {
            b.add(PROPERTY_FOCUS, FhirObject.toArray(focus));
            return this;
        }

        public Builder definition(final String definition) {
            b.add(PROPERTY_DEFINITION, definition);
            return this;
        }

        public MessageHeader build() {
            return new MessageHeader(b.build());
        }
    }

    public static class MessageHeaderDestination extends FhirObject {
        public static final String RESOURCE_TYPE = "MessageHeaderDestination";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_NAME = "name";
        public static final String PROPERTY_TARGET = "target";
        public static final String PROPERTY_ENDPOINT = "endpoint";
        public static final String PROPERTY_RECEIVER = "receiver";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public MessageHeaderDestination(final JsonObject data) {
            super(data);
        }

        /**
         * Unique id for the element within a resource (for internal references).
         * This may be any string value that does not contain spaces.
         */
        public String id() {
            return getString(PROPERTY_ID);
        }

        /**
         * May be used to represent additional information that is not part of
         * the basic definition of the element and that modifies the
         * understanding of the element in which it is contained and/or the
         * understanding of the containing element's descendants. Usually
         * modifier elements provide negation or qualification. To make the use
         * of extensions safe and manageable, there is a strict set of governance
         * applied to the definition and use of extensions. Though any
         * implementer can define an extension, there is a set of requirements
         * that SHALL be met as part of the definition of the extension.
         * Applications processing a resource are required to check for modifier
         * extensions.
         *
         * Modifier extensions SHALL NOT change the meaning of any elements on
         * Resource or DomainResource (including cannot change the meaning of
         * modifierExtension itself).
         */
        public java.util.List<Extension> modifierExtension() {
            return getList(Extension.class, PROPERTY_MODIFIER_EXTENSION);
        }

        /**
         * Human-readable name for the target system.
         */
        public String name() {
            return getString(PROPERTY_NAME);
        }

        /**
         * Identifies the target end system in situations where the initial
         * message transmission is to an intermediary system.
         */
        public Reference target() {
            return getObject(Reference.class, PROPERTY_TARGET);
        }

        /**
         * Indicates where the message should be routed to.
         */
        public String endpoint() {
            return getString(PROPERTY_ENDPOINT);
        }

        /**
         * Allows data conveyed by a message to be addressed to a particular
         * person or department when routing to a specific application isn't
         * sufficient.
         */
        public Reference receiver() {
            return getObject(Reference.class, PROPERTY_RECEIVER);
        }

        public static class Builder {
            private final JsonObjectBuilder b;

            private Builder() {
                b = Json.createObjectBuilder();
            }

            private Builder(final JsonObject data) {
                b = Json.createObjectBuilder(data);
            }

            public Builder id(final String id) {
                b.add(PROPERTY_ID, id);
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder name(final String name) {
                b.add(PROPERTY_NAME, name);
                return this;
            }

            public Builder target(final Reference target) {
                b.add(PROPERTY_TARGET, target);
                return this;
            }

            public Builder endpoint(final String endpoint) {
                b.add(PROPERTY_ENDPOINT, endpoint);
                return this;
            }

            public Builder receiver(final Reference receiver) {
                b.add(PROPERTY_RECEIVER, receiver);
                return this;
            }

            public MessageHeaderDestination build() {
                return new MessageHeaderDestination(b.build());
            }
        }
    }

    public static class MessageHeaderResponse extends FhirObject {
        public static final String RESOURCE_TYPE = "MessageHeaderResponse";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_IDENTIFIER = "identifier";
        public static final String PROPERTY_CODE = "code";
        public static final String PROPERTY_DETAILS = "details";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public MessageHeaderResponse(final JsonObject data) {
            super(data);
        }

        /**
         * Unique id for the element within a resource (for internal references).
         * This may be any string value that does not contain spaces.
         */
        public String id() {
            return getString(PROPERTY_ID);
        }

        /**
         * May be used to represent additional information that is not part of
         * the basic definition of the element and that modifies the
         * understanding of the element in which it is contained and/or the
         * understanding of the containing element's descendants. Usually
         * modifier elements provide negation or qualification. To make the use
         * of extensions safe and manageable, there is a strict set of governance
         * applied to the definition and use of extensions. Though any
         * implementer can define an extension, there is a set of requirements
         * that SHALL be met as part of the definition of the extension.
         * Applications processing a resource are required to check for modifier
         * extensions.
         *
         * Modifier extensions SHALL NOT change the meaning of any elements on
         * Resource or DomainResource (including cannot change the meaning of
         * modifierExtension itself).
         */
        public java.util.List<Extension> modifierExtension() {
            return getList(Extension.class, PROPERTY_MODIFIER_EXTENSION);
        }

        /**
         * The MessageHeader.id of the message to which this message is a
         * response.
         */
        public String identifier() {
            return getString(PROPERTY_IDENTIFIER);
        }

        /**
         * Code that identifies the type of response to the message - whether it
         * was successful or not, and whether it should be resent or not.
         */
        public String code() {
            return getString(PROPERTY_CODE);
        }

        /**
         * Full details of any issues found in the message.
         */
        public Reference details() {
            return getObject(Reference.class, PROPERTY_DETAILS);
        }

        public static class Builder {
            private final JsonObjectBuilder b;

            private Builder() {
                b = Json.createObjectBuilder();
            }

            private Builder(final JsonObject data) {
                b = Json.createObjectBuilder(data);
            }

            public Builder id(final String id) {
                b.add(PROPERTY_ID, id);
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder identifier(final String identifier) {
                b.add(PROPERTY_IDENTIFIER, identifier);
                return this;
            }

            public Builder code(final String code) {
                b.add(PROPERTY_CODE, code);
                return this;
            }

            public Builder details(final Reference details) {
                b.add(PROPERTY_DETAILS, details);
                return this;
            }

            public MessageHeaderResponse build() {
                return new MessageHeaderResponse(b.build());
            }
        }
    }

    public static class MessageHeaderSource extends FhirObject {
        public static final String RESOURCE_TYPE = "MessageHeaderSource";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_NAME = "name";
        public static final String PROPERTY_SOFTWARE = "software";
        public static final String PROPERTY_VERSION = "version";
        public static final String PROPERTY_CONTACT = "contact";
        public static final String PROPERTY_ENDPOINT = "endpoint";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public MessageHeaderSource(final JsonObject data) {
            super(data);
        }

        /**
         * Unique id for the element within a resource (for internal references).
         * This may be any string value that does not contain spaces.
         */
        public String id() {
            return getString(PROPERTY_ID);
        }

        /**
         * May be used to represent additional information that is not part of
         * the basic definition of the element and that modifies the
         * understanding of the element in which it is contained and/or the
         * understanding of the containing element's descendants. Usually
         * modifier elements provide negation or qualification. To make the use
         * of extensions safe and manageable, there is a strict set of governance
         * applied to the definition and use of extensions. Though any
         * implementer can define an extension, there is a set of requirements
         * that SHALL be met as part of the definition of the extension.
         * Applications processing a resource are required to check for modifier
         * extensions.
         *
         * Modifier extensions SHALL NOT change the meaning of any elements on
         * Resource or DomainResource (including cannot change the meaning of
         * modifierExtension itself).
         */
        public java.util.List<Extension> modifierExtension() {
            return getList(Extension.class, PROPERTY_MODIFIER_EXTENSION);
        }

        /**
         * Human-readable name for the source system.
         */
        public String name() {
            return getString(PROPERTY_NAME);
        }

        /**
         * May include configuration or other information useful in debugging.
         */
        public String software() {
            return getString(PROPERTY_SOFTWARE);
        }

        /**
         * Can convey versions of multiple systems in situations where a message
         * passes through multiple hands.
         */
        public String version() {
            return getString(PROPERTY_VERSION);
        }

        /**
         * An e-mail, phone, website or other contact point to use to resolve
         * issues with message communications.
         */
        public ContactPoint contact() {
            return getObject(ContactPoint.class, PROPERTY_CONTACT);
        }

        /**
         * Identifies the routing target to send acknowledgements to.
         */
        public String endpoint() {
            return getString(PROPERTY_ENDPOINT);
        }

        public static class Builder {
            private final JsonObjectBuilder b;

            private Builder() {
                b = Json.createObjectBuilder();
            }

            private Builder(final JsonObject data) {
                b = Json.createObjectBuilder(data);
            }

            public Builder id(final String id) {
                b.add(PROPERTY_ID, id);
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder name(final String name) {
                b.add(PROPERTY_NAME, name);
                return this;
            }

            public Builder software(final String software) {
                b.add(PROPERTY_SOFTWARE, software);
                return this;
            }

            public Builder version(final String version) {
                b.add(PROPERTY_VERSION, version);
                return this;
            }

            public Builder contact(final ContactPoint contact) {
                b.add(PROPERTY_CONTACT, contact);
                return this;
            }

            public Builder endpoint(final String endpoint) {
                b.add(PROPERTY_ENDPOINT, endpoint);
                return this;
            }

            public MessageHeaderSource build() {
                return new MessageHeaderSource(b.build());
            }
        }
    }
}