/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.r4.types;

import jakarta.json.JsonObject;

import com.medplum.fhir.r4.FhirPropertyNames;

/**
 * A JSON object that represents a cryptographic key. The members of the
 * object represent properties of the key, including its value.
 */
public class JsonWebKey extends FhirResource {
    public static final String RESOURCE_TYPE = "JsonWebKey";

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(final JsonObject data) {
        return new Builder(data);
    }

    public JsonWebKey(final JsonObject data) {
        super(data);
    }

    /**
     * Whether this key is in active use.
     */
    public Boolean active() {
        return data.getBoolean(FhirPropertyNames.PROPERTY_ACTIVE);
    }

    /**
     * The specific cryptographic algorithm used with the key.
     */
    public String alg() {
        return getString(FhirPropertyNames.PROPERTY_ALG);
    }

    /**
     * The family of cryptographic algorithms used with the key.
     */
    public String kty() {
        return getString(FhirPropertyNames.PROPERTY_KTY);
    }

    /**
     * How the key was meant to be used; sig represents the signature.
     */
    public String use() {
        return getString(FhirPropertyNames.PROPERTY_USE);
    }

    /**
     * The operation(s) for which the key is intended to be used.
     */
    public String keyOps() {
        return getString(FhirPropertyNames.PROPERTY_KEY_OPS);
    }

    /**
     * The x.509 certificate chain. The first entry in the array is the
     * certificate to use for token verification; the other certificates can
     * be used to verify this first certificate.
     */
    public java.util.List<String> x5c() {
        return getList(String.class, FhirPropertyNames.PROPERTY_X5C);
    }

    /**
     * The modulus for the RSA public key.
     */
    public String n() {
        return getString(FhirPropertyNames.PROPERTY_N);
    }

    /**
     * The exponent for the RSA public key.
     */
    public String e() {
        return getString(FhirPropertyNames.PROPERTY_E);
    }

    /**
     * The unique identifier for the key.
     */
    public String kid() {
        return getString(FhirPropertyNames.PROPERTY_KID);
    }

    /**
     * The thumbprint of the x.509 cert (SHA-1 thumbprint).
     */
    public String x5t() {
        return getString(FhirPropertyNames.PROPERTY_X5T);
    }

    /**
     * The exponent for the RSA private key.
     */
    public String d() {
        return getString(FhirPropertyNames.PROPERTY_D);
    }

    /**
     * The first prime factor.
     */
    public String p() {
        return getString(FhirPropertyNames.PROPERTY_P);
    }

    /**
     * The second prime factor.
     */
    public String q() {
        return getString(FhirPropertyNames.PROPERTY_Q);
    }

    /**
     * The first factor CRT exponent.
     */
    public String dp() {
        return getString(FhirPropertyNames.PROPERTY_DP);
    }

    /**
     * The second factor CRT exponent.
     */
    public String dq() {
        return getString(FhirPropertyNames.PROPERTY_DQ);
    }

    /**
     * The first CRT coefficient.
     */
    public String qi() {
        return getString(FhirPropertyNames.PROPERTY_QI);
    }

    public static final class Builder extends FhirResource.Builder<JsonWebKey, JsonWebKey.Builder> {

        private Builder() {
            super(RESOURCE_TYPE);
        }

        private Builder(final JsonObject data) {
            super(RESOURCE_TYPE, data);
        }

        public Builder active(final Boolean active) {
            b.add(FhirPropertyNames.PROPERTY_ACTIVE, active);
            return this;
        }

        public Builder alg(final String alg) {
            b.add(FhirPropertyNames.PROPERTY_ALG, alg);
            return this;
        }

        public Builder kty(final String kty) {
            b.add(FhirPropertyNames.PROPERTY_KTY, kty);
            return this;
        }

        public Builder use(final String use) {
            b.add(FhirPropertyNames.PROPERTY_USE, use);
            return this;
        }

        public Builder keyOps(final String keyOps) {
            b.add(FhirPropertyNames.PROPERTY_KEY_OPS, keyOps);
            return this;
        }

        public Builder x5c(final java.util.List<String> x5c) {
            b.add(FhirPropertyNames.PROPERTY_X5C, FhirObject.toStringArray(x5c));
            return this;
        }

        public Builder n(final String n) {
            b.add(FhirPropertyNames.PROPERTY_N, n);
            return this;
        }

        public Builder e(final String e) {
            b.add(FhirPropertyNames.PROPERTY_E, e);
            return this;
        }

        public Builder kid(final String kid) {
            b.add(FhirPropertyNames.PROPERTY_KID, kid);
            return this;
        }

        public Builder x5t(final String x5t) {
            b.add(FhirPropertyNames.PROPERTY_X5T, x5t);
            return this;
        }

        public Builder d(final String d) {
            b.add(FhirPropertyNames.PROPERTY_D, d);
            return this;
        }

        public Builder p(final String p) {
            b.add(FhirPropertyNames.PROPERTY_P, p);
            return this;
        }

        public Builder q(final String q) {
            b.add(FhirPropertyNames.PROPERTY_Q, q);
            return this;
        }

        public Builder dp(final String dp) {
            b.add(FhirPropertyNames.PROPERTY_DP, dp);
            return this;
        }

        public Builder dq(final String dq) {
            b.add(FhirPropertyNames.PROPERTY_DQ, dq);
            return this;
        }

        public Builder qi(final String qi) {
            b.add(FhirPropertyNames.PROPERTY_QI, qi);
            return this;
        }

        public JsonWebKey build() {
            return new JsonWebKey(b.build());
        }
    }
}