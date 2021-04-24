/*
 * Generated by com.medplum.generator.Generator
 * Do not edit manually.
 */

package com.medplum.fhir.types;

import jakarta.json.JsonObject;

public class JsonWebKey extends FhirResource {
    public static final String RESOURCE_TYPE = "JsonWebKey";
    public static final String PROPERTY_RESOURCE_TYPE = "resourceType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_ALG = "alg";
    public static final String PROPERTY_KTY = "kty";
    public static final String PROPERTY_USE = "use";
    public static final String PROPERTY_KEY_OPS = "key_ops";
    public static final String PROPERTY_X5C = "x5c";
    public static final String PROPERTY_N = "n";
    public static final String PROPERTY_E = "e";
    public static final String PROPERTY_KID = "kid";
    public static final String PROPERTY_X5T = "x5t";
    public static final String PROPERTY_D = "d";
    public static final String PROPERTY_P = "p";
    public static final String PROPERTY_Q = "q";
    public static final String PROPERTY_DP = "dp";
    public static final String PROPERTY_DQ = "dq";
    public static final String PROPERTY_QI = "qi";

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
        return data.getBoolean(PROPERTY_ACTIVE);
    }

    /**
     * The specific cryptographic algorithm used with the key.
     */
    public String alg() {
        return getString(PROPERTY_ALG);
    }

    /**
     * The family of cryptographic algorithms used with the key.
     */
    public String kty() {
        return getString(PROPERTY_KTY);
    }

    /**
     * How the key was meant to be used; sig represents the signature.
     */
    public String use() {
        return getString(PROPERTY_USE);
    }

    /**
     * The operation(s) for which the key is intended to be used.
     */
    public String keyOps() {
        return getString(PROPERTY_KEY_OPS);
    }

    /**
     * The x.509 certificate chain. The first entry in the array is the
     * certificate to use for token verification; the other certificates can
     * be used to verify this first certificate.
     */
    public java.util.List<String> x5c() {
        return getList(String.class, PROPERTY_X5C);
    }

    /**
     * The modulus for the RSA public key.
     */
    public String n() {
        return getString(PROPERTY_N);
    }

    /**
     * The exponent for the RSA public key.
     */
    public String e() {
        return getString(PROPERTY_E);
    }

    /**
     * The unique identifier for the key.
     */
    public String kid() {
        return getString(PROPERTY_KID);
    }

    /**
     * The thumbprint of the x.509 cert (SHA-1 thumbprint).
     */
    public String x5t() {
        return getString(PROPERTY_X5T);
    }

    /**
     * The exponent for the RSA private key.
     */
    public String d() {
        return getString(PROPERTY_D);
    }

    /**
     * The first prime factor.
     */
    public String p() {
        return getString(PROPERTY_P);
    }

    /**
     * The second prime factor.
     */
    public String q() {
        return getString(PROPERTY_Q);
    }

    /**
     * The first factor CRT exponent.
     */
    public String dp() {
        return getString(PROPERTY_DP);
    }

    /**
     * The second factor CRT exponent.
     */
    public String dq() {
        return getString(PROPERTY_DQ);
    }

    /**
     * The first CRT coefficient.
     */
    public String qi() {
        return getString(PROPERTY_QI);
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

        public Builder active(final Boolean active) {
            b.add(PROPERTY_ACTIVE, active);
            return this;
        }

        public Builder alg(final String alg) {
            b.add(PROPERTY_ALG, alg);
            return this;
        }

        public Builder kty(final String kty) {
            b.add(PROPERTY_KTY, kty);
            return this;
        }

        public Builder use(final String use) {
            b.add(PROPERTY_USE, use);
            return this;
        }

        public Builder keyOps(final String keyOps) {
            b.add(PROPERTY_KEY_OPS, keyOps);
            return this;
        }

        public Builder x5c(final java.util.List<String> x5c) {
            b.add(PROPERTY_X5C, FhirObject.toStringArray(x5c));
            return this;
        }

        public Builder n(final String n) {
            b.add(PROPERTY_N, n);
            return this;
        }

        public Builder e(final String e) {
            b.add(PROPERTY_E, e);
            return this;
        }

        public Builder kid(final String kid) {
            b.add(PROPERTY_KID, kid);
            return this;
        }

        public Builder x5t(final String x5t) {
            b.add(PROPERTY_X5T, x5t);
            return this;
        }

        public Builder d(final String d) {
            b.add(PROPERTY_D, d);
            return this;
        }

        public Builder p(final String p) {
            b.add(PROPERTY_P, p);
            return this;
        }

        public Builder q(final String q) {
            b.add(PROPERTY_Q, q);
            return this;
        }

        public Builder dp(final String dp) {
            b.add(PROPERTY_DP, dp);
            return this;
        }

        public Builder dq(final String dq) {
            b.add(PROPERTY_DQ, dq);
            return this;
        }

        public Builder qi(final String qi) {
            b.add(PROPERTY_QI, qi);
            return this;
        }

        public JsonWebKey build() {
            return new JsonWebKey(b.build());
        }
    }
}