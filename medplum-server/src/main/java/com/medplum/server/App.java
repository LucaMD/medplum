package com.medplum.server;

import java.sql.SQLException;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.keys.resolvers.JwksVerificationKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;
import org.jose4j.lang.JoseException;

import com.medplum.server.fhir.repo.BinaryStorage;
import com.medplum.server.fhir.repo.FileSystemBinaryStorage;
import com.medplum.server.fhir.repo.JdbcRepository;
import com.medplum.server.fhir.repo.JdbcRepositoryFactory;
import com.medplum.server.fhir.repo.Repository;
import com.medplum.server.security.JwkManager;
import com.medplum.server.security.OAuthService;
import com.medplum.server.services.DebugEmailService;
import com.medplum.server.services.EmailService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * The App class represents the JAX-RS Application configuration.
 */
public class App extends ResourceConfig {
    private final DataSource dataSource;
    private final JsonWebKeySet jwks;
    private final JwksVerificationKeyResolver keyResolver;

    public App(final Map<String, Object> properties) throws JoseException {
        // Prefer IPv4
        System.setProperty("java.net.preferIPv4Stack", "true");

        // Need to allow CORS headers to be sent
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        // Use UTC by default
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // Jersey config settings
        property(ServerProperties.WADL_FEATURE_DISABLE, "true");
        property(MustacheMvcFeature.TEMPLATE_BASE_PATH, "templates");
        register(MustacheMvcFeature.class);

        // Pass all config properties to Jersey
        addProperties(properties);

        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl((String) properties.get(ConfigSettings.JDBC_URL));
        config.setUsername((String) properties.get(ConfigSettings.JDBC_USERNAME));
        config.setPassword((String) properties.get(ConfigSettings.JDBC_PASSWORD));
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);

        try (final JdbcRepository repo = getRepo()) {
            repo.createTables();
            jwks = JwkManager.initKeys(repo);
        }

        keyResolver = new JwksVerificationKeyResolver(jwks.getJsonWebKeys());

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(dataSource).to(DataSource.class);
                bind(jwks).to(JsonWebKeySet.class);
                bind(keyResolver).to(VerificationKeyResolver.class);
                bindFactory(JdbcRepositoryFactory.class).to(Repository.class).in(RequestScoped.class);
                bind(FileSystemBinaryStorage.class).to(BinaryStorage.class);
                bind(DebugEmailService.class).to(EmailService.class);
                bind(OAuthService.class).to(OAuthService.class).in(RequestScoped.class);
            }
        });

        packages("com.medplum.server");
    }

    public JdbcRepository getRepo() {
        try {
            return new JdbcRepository(dataSource.getConnection());
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public OAuthService getOAuth() {
        return new OAuthService(getRepo(), jwks, keyResolver);
    }
}