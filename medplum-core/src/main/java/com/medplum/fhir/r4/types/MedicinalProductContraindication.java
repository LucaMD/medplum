/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import jakarta.json.JsonObject;

import com.medplum.fhir.r4.FhirPropertyNames;

/**
 * The clinical particulars - indications, contraindications etc. of a
 * medicinal product, including for regulatory purposes.
 */
public class MedicinalProductContraindication extends DomainResource {
    public static final String RESOURCE_TYPE = "MedicinalProductContraindication";

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(final JsonObject data) {
        return new Builder(data);
    }

    public MedicinalProductContraindication(final JsonObject data) {
        super(data);
    }

    /**
     * The medication for which this is an indication.
     */
    public java.util.List<Reference> subject() {
        return getList(Reference.class, FhirPropertyNames.PROPERTY_SUBJECT);
    }

    /**
     * The disease, symptom or procedure for the contraindication.
     */
    public CodeableConcept disease() {
        return getObject(CodeableConcept.class, FhirPropertyNames.PROPERTY_DISEASE);
    }

    /**
     * The status of the disease or symptom for the contraindication.
     */
    public CodeableConcept diseaseStatus() {
        return getObject(CodeableConcept.class, FhirPropertyNames.PROPERTY_DISEASE_STATUS);
    }

    /**
     * A comorbidity (concurrent condition) or coinfection.
     */
    public java.util.List<CodeableConcept> comorbidity() {
        return getList(CodeableConcept.class, FhirPropertyNames.PROPERTY_COMORBIDITY);
    }

    /**
     * Information about the use of the medicinal product in relation to
     * other therapies as part of the indication.
     */
    public java.util.List<Reference> therapeuticIndication() {
        return getList(Reference.class, FhirPropertyNames.PROPERTY_THERAPEUTIC_INDICATION);
    }

    /**
     * Information about the use of the medicinal product in relation to
     * other therapies described as part of the indication.
     */
    public java.util.List<MedicinalProductContraindicationOtherTherapy> otherTherapy() {
        return getList(MedicinalProductContraindicationOtherTherapy.class, FhirPropertyNames.PROPERTY_OTHER_THERAPY);
    }

    /**
     * The population group to which this applies.
     */
    public java.util.List<Population> population() {
        return getList(Population.class, FhirPropertyNames.PROPERTY_POPULATION);
    }

    public static final class Builder extends DomainResource.Builder<MedicinalProductContraindication, MedicinalProductContraindication.Builder> {

        private Builder() {
            super(RESOURCE_TYPE);
        }

        private Builder(final JsonObject data) {
            super(RESOURCE_TYPE, data);
        }

        public Builder subject(final java.util.List<Reference> subject) {
            b.add(FhirPropertyNames.PROPERTY_SUBJECT, FhirObject.toArray(subject));
            return this;
        }

        public Builder disease(final CodeableConcept disease) {
            b.add(FhirPropertyNames.PROPERTY_DISEASE, disease);
            return this;
        }

        public Builder diseaseStatus(final CodeableConcept diseaseStatus) {
            b.add(FhirPropertyNames.PROPERTY_DISEASE_STATUS, diseaseStatus);
            return this;
        }

        public Builder comorbidity(final java.util.List<CodeableConcept> comorbidity) {
            b.add(FhirPropertyNames.PROPERTY_COMORBIDITY, FhirObject.toArray(comorbidity));
            return this;
        }

        public Builder therapeuticIndication(final java.util.List<Reference> therapeuticIndication) {
            b.add(FhirPropertyNames.PROPERTY_THERAPEUTIC_INDICATION, FhirObject.toArray(therapeuticIndication));
            return this;
        }

        public Builder otherTherapy(final java.util.List<MedicinalProductContraindicationOtherTherapy> otherTherapy) {
            b.add(FhirPropertyNames.PROPERTY_OTHER_THERAPY, FhirObject.toArray(otherTherapy));
            return this;
        }

        public Builder population(final java.util.List<Population> population) {
            b.add(FhirPropertyNames.PROPERTY_POPULATION, FhirObject.toArray(population));
            return this;
        }

        public MedicinalProductContraindication build() {
            return new MedicinalProductContraindication(b.build());
        }
    }

    /**
     * The clinical particulars - indications, contraindications etc. of a
     * medicinal product, including for regulatory purposes.
     */
    public static class MedicinalProductContraindicationOtherTherapy extends FhirObject {
        public static final String RESOURCE_TYPE = "MedicinalProductContraindicationOtherTherapy";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public MedicinalProductContraindicationOtherTherapy(final JsonObject data) {
            super(data);
        }

        /**
         * Unique id for the element within a resource (for internal references).
         * This may be any string value that does not contain spaces.
         */
        public String id() {
            return getString(FhirPropertyNames.PROPERTY_ID);
        }

        /**
         * May be used to represent additional information that is not part of
         * the basic definition of the element. To make the use of extensions
         * safe and manageable, there is a strict set of governance  applied to
         * the definition and use of extensions. Though any implementer can
         * define an extension, there is a set of requirements that SHALL be met
         * as part of the definition of the extension.
         */
        public java.util.List<Extension> extension() {
            return getList(Extension.class, FhirPropertyNames.PROPERTY_EXTENSION);
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
            return getList(Extension.class, FhirPropertyNames.PROPERTY_MODIFIER_EXTENSION);
        }

        /**
         * The type of relationship between the medicinal product indication or
         * contraindication and another therapy.
         */
        public CodeableConcept therapyRelationshipType() {
            return getObject(CodeableConcept.class, FhirPropertyNames.PROPERTY_THERAPY_RELATIONSHIP_TYPE);
        }

        /**
         * Reference to a specific medication (active substance, medicinal
         * product or class of products) as part of an indication or
         * contraindication.
         */
        public CodeableConcept medicationCodeableConcept() {
            return getObject(CodeableConcept.class, FhirPropertyNames.PROPERTY_MEDICATION_CODEABLE_CONCEPT);
        }

        /**
         * Reference to a specific medication (active substance, medicinal
         * product or class of products) as part of an indication or
         * contraindication.
         */
        public Reference medicationReference() {
            return getObject(Reference.class, FhirPropertyNames.PROPERTY_MEDICATION_REFERENCE);
        }

        public static final class Builder extends FhirObject.Builder<MedicinalProductContraindicationOtherTherapy,
                MedicinalProductContraindicationOtherTherapy.Builder> {

            private Builder() {
                super();
            }

            private Builder(final JsonObject data) {
                super(data);
            }

            public Builder id(final String id) {
                b.add(FhirPropertyNames.PROPERTY_ID, id);
                return this;
            }

            public Builder extension(final java.util.List<Extension> extension) {
                b.add(FhirPropertyNames.PROPERTY_EXTENSION, FhirObject.toArray(extension));
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(FhirPropertyNames.PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder therapyRelationshipType(final CodeableConcept therapyRelationshipType) {
                b.add(FhirPropertyNames.PROPERTY_THERAPY_RELATIONSHIP_TYPE, therapyRelationshipType);
                return this;
            }

            public Builder medicationCodeableConcept(final CodeableConcept medicationCodeableConcept) {
                b.add(FhirPropertyNames.PROPERTY_MEDICATION_CODEABLE_CONCEPT, medicationCodeableConcept);
                return this;
            }

            public Builder medicationReference(final Reference medicationReference) {
                b.add(FhirPropertyNames.PROPERTY_MEDICATION_REFERENCE, medicationReference);
                return this;
            }

            public MedicinalProductContraindicationOtherTherapy build() {
                return new MedicinalProductContraindicationOtherTherapy(b.build());
            }
        }
    }
}