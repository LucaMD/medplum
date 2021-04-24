/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import jakarta.json.JsonObject;

public class Flag extends FhirResource {
    public static final String RESOURCE_TYPE = "Flag";
    public static final String PROPERTY_RESOURCE_TYPE = "resourceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_META = "meta";
    public static final String PROPERTY_IMPLICIT_RULES = "implicitRules";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_TEXT = "text";
    public static final String PROPERTY_CONTAINED = "contained";
    public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
    public static final String PROPERTY_IDENTIFIER = "identifier";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_CATEGORY = "category";
    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_SUBJECT = "subject";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_ENCOUNTER = "encounter";
    public static final String PROPERTY_AUTHOR = "author";

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(final JsonObject data) {
        return new Builder(data);
    }

    public Flag(final JsonObject data) {
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
     * Business identifiers assigned to this flag by the performer or other
     * systems which remain constant as the resource is updated and
     * propagates from server to server.
     */
    public java.util.List<Identifier> identifier() {
        return getList(Identifier.class, PROPERTY_IDENTIFIER);
    }

    /**
     * Supports basic workflow.
     */
    public String status() {
        return getString(PROPERTY_STATUS);
    }

    /**
     * Allows a flag to be divided into different categories like clinical,
     * administrative etc. Intended to be used as a means of filtering which
     * flags are displayed to particular user or in a given context.
     */
    public java.util.List<CodeableConcept> category() {
        return getList(CodeableConcept.class, PROPERTY_CATEGORY);
    }

    /**
     * The coded value or textual component of the flag to display to the
     * user.
     */
    public CodeableConcept code() {
        return getObject(CodeableConcept.class, PROPERTY_CODE);
    }

    /**
     * The patient, location, group, organization, or practitioner etc. this
     * is about record this flag is associated with.
     */
    public Reference subject() {
        return getObject(Reference.class, PROPERTY_SUBJECT);
    }

    /**
     * The period of time from the activation of the flag to inactivation of
     * the flag. If the flag is active, the end of the period should be
     * unspecified.
     */
    public Period period() {
        return getObject(Period.class, PROPERTY_PERIOD);
    }

    /**
     * This alert is only relevant during the encounter.
     */
    public Reference encounter() {
        return getObject(Reference.class, PROPERTY_ENCOUNTER);
    }

    /**
     * The person, organization or device that created the flag.
     */
    public Reference author() {
        return getObject(Reference.class, PROPERTY_AUTHOR);
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

        public Builder identifier(final java.util.List<Identifier> identifier) {
            b.add(PROPERTY_IDENTIFIER, FhirObject.toArray(identifier));
            return this;
        }

        public Builder status(final String status) {
            b.add(PROPERTY_STATUS, status);
            return this;
        }

        public Builder category(final java.util.List<CodeableConcept> category) {
            b.add(PROPERTY_CATEGORY, FhirObject.toArray(category));
            return this;
        }

        public Builder code(final CodeableConcept code) {
            b.add(PROPERTY_CODE, code);
            return this;
        }

        public Builder subject(final Reference subject) {
            b.add(PROPERTY_SUBJECT, subject);
            return this;
        }

        public Builder period(final Period period) {
            b.add(PROPERTY_PERIOD, period);
            return this;
        }

        public Builder encounter(final Reference encounter) {
            b.add(PROPERTY_ENCOUNTER, encounter);
            return this;
        }

        public Builder author(final Reference author) {
            b.add(PROPERTY_AUTHOR, author);
            return this;
        }

        public Flag build() {
            return new Flag(b.build());
        }
    }
}