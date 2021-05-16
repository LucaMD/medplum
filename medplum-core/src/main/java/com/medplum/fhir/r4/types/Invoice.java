/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/**
 * Invoice containing collected ChargeItems from an Account with
 * calculated individual and total price for Billing purpose.
 */
public class Invoice extends FhirResource {
    public static final String RESOURCE_TYPE = "Invoice";
    public static final String PROPERTY_RESOURCE_TYPE = "resourceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_META = "meta";
    public static final String PROPERTY_IMPLICIT_RULES = "implicitRules";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_TEXT = "text";
    public static final String PROPERTY_CONTAINED = "contained";
    public static final String PROPERTY_EXTENSION = "extension";
    public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
    public static final String PROPERTY_IDENTIFIER = "identifier";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_CANCELLED_REASON = "cancelledReason";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_SUBJECT = "subject";
    public static final String PROPERTY_RECIPIENT = "recipient";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_PARTICIPANT = "participant";
    public static final String PROPERTY_ISSUER = "issuer";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_LINE_ITEM = "lineItem";
    public static final String PROPERTY_TOTAL_PRICE_COMPONENT = "totalPriceComponent";
    public static final String PROPERTY_TOTAL_NET = "totalNet";
    public static final String PROPERTY_TOTAL_GROSS = "totalGross";
    public static final String PROPERTY_PAYMENT_TERMS = "paymentTerms";
    public static final String PROPERTY_NOTE = "note";

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(final JsonObject data) {
        return new Builder(data);
    }

    public Invoice(final JsonObject data) {
        super(data);
    }

    /**
     * A reference to a set of rules that were followed when the resource was
     * constructed, and which must be understood when processing the content.
     * Often, this is a reference to an implementation guide that defines the
     * special rules along with other profiles etc.
     */
    public java.net.URI implicitRules() {
        return getUri(PROPERTY_IMPLICIT_RULES);
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
     * contain sufficient detail to make it &quot;clinically safe&quot; for a human to
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
     * the basic definition of the resource. To make the use of extensions
     * safe and manageable, there is a strict set of governance  applied to
     * the definition and use of extensions. Though any implementer can
     * define an extension, there is a set of requirements that SHALL be met
     * as part of the definition of the extension.
     */
    public java.util.List<Extension> extension() {
        return getList(Extension.class, PROPERTY_EXTENSION);
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
     * Identifier of this Invoice, often used for reference in correspondence
     * about this invoice or for tracking of payments.
     */
    public java.util.List<Identifier> identifier() {
        return getList(Identifier.class, PROPERTY_IDENTIFIER);
    }

    /**
     * The current state of the Invoice.
     */
    public String status() {
        return getString(PROPERTY_STATUS);
    }

    /**
     * In case of Invoice cancellation a reason must be given (entered in
     * error, superseded by corrected invoice etc.).
     */
    public String cancelledReason() {
        return getString(PROPERTY_CANCELLED_REASON);
    }

    /**
     * Type of Invoice depending on domain, realm an usage (e.g.
     * internal/external, dental, preliminary).
     */
    public CodeableConcept type() {
        return getObject(CodeableConcept.class, PROPERTY_TYPE);
    }

    /**
     * The individual or set of individuals receiving the goods and services
     * billed in this invoice.
     */
    public Reference subject() {
        return getObject(Reference.class, PROPERTY_SUBJECT);
    }

    /**
     * The individual or Organization responsible for balancing of this
     * invoice.
     */
    public Reference recipient() {
        return getObject(Reference.class, PROPERTY_RECIPIENT);
    }

    /**
     * Date/time(s) of when this Invoice was posted.
     */
    public java.time.Instant date() {
        return getInstant(PROPERTY_DATE);
    }

    /**
     * Indicates who or what performed or participated in the charged
     * service.
     */
    public java.util.List<InvoiceParticipant> participant() {
        return getList(InvoiceParticipant.class, PROPERTY_PARTICIPANT);
    }

    /**
     * The organizationissuing the Invoice.
     */
    public Reference issuer() {
        return getObject(Reference.class, PROPERTY_ISSUER);
    }

    /**
     * Account which is supposed to be balanced with this Invoice.
     */
    public Reference account() {
        return getObject(Reference.class, PROPERTY_ACCOUNT);
    }

    /**
     * Each line item represents one charge for goods and services rendered.
     * Details such as date, code and amount are found in the referenced
     * ChargeItem resource.
     */
    public java.util.List<InvoiceLineItem> lineItem() {
        return getList(InvoiceLineItem.class, PROPERTY_LINE_ITEM);
    }

    /**
     * The total amount for the Invoice may be calculated as the sum of the
     * line items with surcharges/deductions that apply in certain
     * conditions.  The priceComponent element can be used to offer
     * transparency to the recipient of the Invoice of how the total price
     * was calculated.
     */
    public java.util.List<InvoicePriceComponent> totalPriceComponent() {
        return getList(InvoicePriceComponent.class, PROPERTY_TOTAL_PRICE_COMPONENT);
    }

    /**
     * Invoice total , taxes excluded.
     */
    public Money totalNet() {
        return getObject(Money.class, PROPERTY_TOTAL_NET);
    }

    /**
     * Invoice total, tax included.
     */
    public Money totalGross() {
        return getObject(Money.class, PROPERTY_TOTAL_GROSS);
    }

    /**
     * Payment details such as banking details, period of payment,
     * deductibles, methods of payment.
     */
    public String paymentTerms() {
        return getString(PROPERTY_PAYMENT_TERMS);
    }

    /**
     * Comments made about the invoice by the issuer, subject, or other
     * participants.
     */
    public java.util.List<Annotation> note() {
        return getList(Annotation.class, PROPERTY_NOTE);
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

        public Builder implicitRules(final java.net.URI implicitRules) {
            b.add(PROPERTY_IMPLICIT_RULES, implicitRules.toString());
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

        public Builder extension(final java.util.List<Extension> extension) {
            b.add(PROPERTY_EXTENSION, FhirObject.toArray(extension));
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

        public Builder cancelledReason(final String cancelledReason) {
            b.add(PROPERTY_CANCELLED_REASON, cancelledReason);
            return this;
        }

        public Builder type(final CodeableConcept type) {
            b.add(PROPERTY_TYPE, type);
            return this;
        }

        public Builder subject(final Reference subject) {
            b.add(PROPERTY_SUBJECT, subject);
            return this;
        }

        public Builder recipient(final Reference recipient) {
            b.add(PROPERTY_RECIPIENT, recipient);
            return this;
        }

        public Builder date(final java.time.Instant date) {
            b.add(PROPERTY_DATE, date.toString());
            return this;
        }

        public Builder participant(final java.util.List<InvoiceParticipant> participant) {
            b.add(PROPERTY_PARTICIPANT, FhirObject.toArray(participant));
            return this;
        }

        public Builder issuer(final Reference issuer) {
            b.add(PROPERTY_ISSUER, issuer);
            return this;
        }

        public Builder account(final Reference account) {
            b.add(PROPERTY_ACCOUNT, account);
            return this;
        }

        public Builder lineItem(final java.util.List<InvoiceLineItem> lineItem) {
            b.add(PROPERTY_LINE_ITEM, FhirObject.toArray(lineItem));
            return this;
        }

        public Builder totalPriceComponent(final java.util.List<InvoicePriceComponent> totalPriceComponent) {
            b.add(PROPERTY_TOTAL_PRICE_COMPONENT, FhirObject.toArray(totalPriceComponent));
            return this;
        }

        public Builder totalNet(final Money totalNet) {
            b.add(PROPERTY_TOTAL_NET, totalNet);
            return this;
        }

        public Builder totalGross(final Money totalGross) {
            b.add(PROPERTY_TOTAL_GROSS, totalGross);
            return this;
        }

        public Builder paymentTerms(final String paymentTerms) {
            b.add(PROPERTY_PAYMENT_TERMS, paymentTerms);
            return this;
        }

        public Builder note(final java.util.List<Annotation> note) {
            b.add(PROPERTY_NOTE, FhirObject.toArray(note));
            return this;
        }

        public Invoice build() {
            return new Invoice(b.build());
        }
    }

    /**
     * Invoice containing collected ChargeItems from an Account with
     * calculated individual and total price for Billing purpose.
     */
    public static class InvoiceLineItem extends FhirObject {
        public static final String RESOURCE_TYPE = "InvoiceLineItem";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_EXTENSION = "extension";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_SEQUENCE = "sequence";
        public static final String PROPERTY_CHARGE_ITEM_REFERENCE = "chargeItemReference";
        public static final String PROPERTY_CHARGE_ITEM_CODEABLE_CONCEPT = "chargeItemCodeableConcept";
        public static final String PROPERTY_PRICE_COMPONENT = "priceComponent";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public InvoiceLineItem(final JsonObject data) {
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
         * the basic definition of the element. To make the use of extensions
         * safe and manageable, there is a strict set of governance  applied to
         * the definition and use of extensions. Though any implementer can
         * define an extension, there is a set of requirements that SHALL be met
         * as part of the definition of the extension.
         */
        public java.util.List<Extension> extension() {
            return getList(Extension.class, PROPERTY_EXTENSION);
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
         * Sequence in which the items appear on the invoice.
         */
        public Integer sequence() {
            return data.getInt(PROPERTY_SEQUENCE);
        }

        /**
         * The ChargeItem contains information such as the billing code, date,
         * amount etc. If no further details are required for the lineItem,
         * inline billing codes can be added using the CodeableConcept data type
         * instead of the Reference.
         */
        public Reference chargeItemReference() {
            return getObject(Reference.class, PROPERTY_CHARGE_ITEM_REFERENCE);
        }

        /**
         * The ChargeItem contains information such as the billing code, date,
         * amount etc. If no further details are required for the lineItem,
         * inline billing codes can be added using the CodeableConcept data type
         * instead of the Reference.
         */
        public CodeableConcept chargeItemCodeableConcept() {
            return getObject(CodeableConcept.class, PROPERTY_CHARGE_ITEM_CODEABLE_CONCEPT);
        }

        /**
         * The price for a ChargeItem may be calculated as a base price with
         * surcharges/deductions that apply in certain conditions. A
         * ChargeItemDefinition resource that defines the prices, factors and
         * conditions that apply to a billing code is currently under
         * development. The priceComponent element can be used to offer
         * transparency to the recipient of the Invoice as to how the prices have
         * been calculated.
         */
        public java.util.List<InvoicePriceComponent> priceComponent() {
            return getList(InvoicePriceComponent.class, PROPERTY_PRICE_COMPONENT);
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

            public Builder extension(final java.util.List<Extension> extension) {
                b.add(PROPERTY_EXTENSION, FhirObject.toArray(extension));
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder sequence(final Integer sequence) {
                b.add(PROPERTY_SEQUENCE, sequence);
                return this;
            }

            public Builder chargeItemReference(final Reference chargeItemReference) {
                b.add(PROPERTY_CHARGE_ITEM_REFERENCE, chargeItemReference);
                return this;
            }

            public Builder chargeItemCodeableConcept(final CodeableConcept chargeItemCodeableConcept) {
                b.add(PROPERTY_CHARGE_ITEM_CODEABLE_CONCEPT, chargeItemCodeableConcept);
                return this;
            }

            public Builder priceComponent(final java.util.List<InvoicePriceComponent> priceComponent) {
                b.add(PROPERTY_PRICE_COMPONENT, FhirObject.toArray(priceComponent));
                return this;
            }

            public InvoiceLineItem build() {
                return new InvoiceLineItem(b.build());
            }
        }
    }

    /**
     * Invoice containing collected ChargeItems from an Account with
     * calculated individual and total price for Billing purpose.
     */
    public static class InvoiceParticipant extends FhirObject {
        public static final String RESOURCE_TYPE = "InvoiceParticipant";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_EXTENSION = "extension";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_ROLE = "role";
        public static final String PROPERTY_ACTOR = "actor";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public InvoiceParticipant(final JsonObject data) {
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
         * the basic definition of the element. To make the use of extensions
         * safe and manageable, there is a strict set of governance  applied to
         * the definition and use of extensions. Though any implementer can
         * define an extension, there is a set of requirements that SHALL be met
         * as part of the definition of the extension.
         */
        public java.util.List<Extension> extension() {
            return getList(Extension.class, PROPERTY_EXTENSION);
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
         * Describes the type of involvement (e.g. transcriptionist, creator
         * etc.). If the invoice has been created automatically, the Participant
         * may be a billing engine or another kind of device.
         */
        public CodeableConcept role() {
            return getObject(CodeableConcept.class, PROPERTY_ROLE);
        }

        /**
         * The device, practitioner, etc. who performed or participated in the
         * service.
         */
        public Reference actor() {
            return getObject(Reference.class, PROPERTY_ACTOR);
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

            public Builder extension(final java.util.List<Extension> extension) {
                b.add(PROPERTY_EXTENSION, FhirObject.toArray(extension));
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder role(final CodeableConcept role) {
                b.add(PROPERTY_ROLE, role);
                return this;
            }

            public Builder actor(final Reference actor) {
                b.add(PROPERTY_ACTOR, actor);
                return this;
            }

            public InvoiceParticipant build() {
                return new InvoiceParticipant(b.build());
            }
        }
    }

    /**
     * Invoice containing collected ChargeItems from an Account with
     * calculated individual and total price for Billing purpose.
     */
    public static class InvoicePriceComponent extends FhirObject {
        public static final String RESOURCE_TYPE = "InvoicePriceComponent";
        public static final String PROPERTY_ID = "id";
        public static final String PROPERTY_EXTENSION = "extension";
        public static final String PROPERTY_MODIFIER_EXTENSION = "modifierExtension";
        public static final String PROPERTY_TYPE = "type";
        public static final String PROPERTY_CODE = "code";
        public static final String PROPERTY_FACTOR = "factor";
        public static final String PROPERTY_AMOUNT = "amount";

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(final JsonObject data) {
            return new Builder(data);
        }

        public InvoicePriceComponent(final JsonObject data) {
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
         * the basic definition of the element. To make the use of extensions
         * safe and manageable, there is a strict set of governance  applied to
         * the definition and use of extensions. Though any implementer can
         * define an extension, there is a set of requirements that SHALL be met
         * as part of the definition of the extension.
         */
        public java.util.List<Extension> extension() {
            return getList(Extension.class, PROPERTY_EXTENSION);
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
         * This code identifies the type of the component.
         */
        public String type() {
            return getString(PROPERTY_TYPE);
        }

        /**
         * A code that identifies the component. Codes may be used to
         * differentiate between kinds of taxes, surcharges, discounts etc.
         */
        public CodeableConcept code() {
            return getObject(CodeableConcept.class, PROPERTY_CODE);
        }

        /**
         * The factor that has been applied on the base price for calculating
         * this component.
         */
        public Double factor() {
            return data.getJsonNumber(PROPERTY_FACTOR).doubleValue();
        }

        /**
         * The amount calculated for this component.
         */
        public Money amount() {
            return getObject(Money.class, PROPERTY_AMOUNT);
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

            public Builder extension(final java.util.List<Extension> extension) {
                b.add(PROPERTY_EXTENSION, FhirObject.toArray(extension));
                return this;
            }

            public Builder modifierExtension(final java.util.List<Extension> modifierExtension) {
                b.add(PROPERTY_MODIFIER_EXTENSION, FhirObject.toArray(modifierExtension));
                return this;
            }

            public Builder type(final String type) {
                b.add(PROPERTY_TYPE, type);
                return this;
            }

            public Builder code(final CodeableConcept code) {
                b.add(PROPERTY_CODE, code);
                return this;
            }

            public Builder factor(final Double factor) {
                b.add(PROPERTY_FACTOR, factor);
                return this;
            }

            public Builder amount(final Money amount) {
                b.add(PROPERTY_AMOUNT, amount);
                return this;
            }

            public InvoicePriceComponent build() {
                return new InvoicePriceComponent(b.build());
            }
        }
    }
}