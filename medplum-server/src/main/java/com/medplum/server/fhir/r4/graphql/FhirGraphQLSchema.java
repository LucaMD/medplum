package com.medplum.server.fhir.r4.graphql;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.medplum.fhir.r4.FhirSchema;
import com.medplum.server.fhir.r4.search.SearchParameters;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLTypeReference;

/**
 * The FhirGraphQLSchema class contains helpers for building GraphQL schemas.
 *
 * There are 2 distinct schema types:
 *   1) Root requests to /fhir/R4/$graphql
 *   2) Resource requests to /fhir/R4/{resourceType}/{id}/$graphql
 *
 * All schemas contain the same "type" definitions.
 *
 * Each schema has a different "query type".
 *
 * For the root request, the "query type" is roughly a list of all resource types.
 *
 * For resource requests, the "query type" is the resource type itself.
 */
public class FhirGraphQLSchema {
    private static final Logger LOG = LoggerFactory.getLogger(FhirGraphQLSchema.class);
    private static final Map<String, GraphQLOutputType> typeCache;
    private static final Map<String, GraphQLSchema> schemaCache;
    private static final GraphQLSchema rootSchema;

    static {
        typeCache = new ConcurrentHashMap<>();
        typeCache.put("base64Binary", Scalars.GraphQLString);
        typeCache.put("boolean", Scalars.GraphQLBoolean);
        typeCache.put("canonical", Scalars.GraphQLString);
        typeCache.put("code", Scalars.GraphQLString);
        typeCache.put("date", Scalars.GraphQLString);
        typeCache.put("dateTime", Scalars.GraphQLString);
        typeCache.put("decimal", Scalars.GraphQLFloat);
        typeCache.put("id", Scalars.GraphQLID);
        typeCache.put("instant", Scalars.GraphQLString);
        typeCache.put("integer", Scalars.GraphQLFloat);
        typeCache.put("markdown", Scalars.GraphQLString);
        typeCache.put("number", Scalars.GraphQLFloat);
        typeCache.put("positiveInt", Scalars.GraphQLFloat);
        typeCache.put("string", Scalars.GraphQLString);
        typeCache.put("time", Scalars.GraphQLString);
        typeCache.put("unsignedInt", Scalars.GraphQLFloat);
        typeCache.put("uri", Scalars.GraphQLString);
        typeCache.put("url", Scalars.GraphQLString);
        typeCache.put("xhtml", Scalars.GraphQLString);

        schemaCache = new ConcurrentHashMap<>();

        rootSchema = buildRootSchema();
    }

    FhirGraphQLSchema() {
        throw new UnsupportedOperationException();
    }

    public static GraphQLSchema getRootSchema() {
        return rootSchema;
    }

    public static GraphQLSchema getResourceTypeSchema(final String resourceType) {
        return schemaCache.computeIfAbsent(resourceType, FhirGraphQLSchema::buildResourceTypeSchema);
    }

    private static GraphQLSchema buildRootSchema() {
        final var builder = GraphQLObjectType.newObject().name("QueryType");

        for (final var resourceType : FhirSchema.getResourceTypes()) {
            final var graphQLType = getGraphQLType(resourceType);
            if (graphQLType == null) {
                continue;
            }

            // Get resource by ID
            builder.field(GraphQLFieldDefinition.newFieldDefinition()
                    .name(resourceType)
                    .type(graphQLType)
                    .argument(GraphQLArgument.newArgument()
                            .name("id")
                            .description(resourceType + " ID")
                            .type(GraphQLNonNull.nonNull(Scalars.GraphQLID))
                            .build())
                    .build());

            // Search resources by search parameters
            final var searchBuilder = GraphQLFieldDefinition.newFieldDefinition()
                    .name(resourceType + "List")
                    .type(GraphQLList.list(graphQLType));

            for (final var param : SearchParameters.getParameters(resourceType)) {
                searchBuilder.argument(GraphQLArgument.newArgument()
                        .name(param.code().replace("-", "_"))
                        .description(param.description())
                        .type(Scalars.GraphQLString)
                        .build());
            }

            builder.field(searchBuilder.build());
        }

        return buildSchema(builder.build());
    }

    private static GraphQLSchema buildResourceTypeSchema(final String resourceType) {
        final var graphQLType = (GraphQLObjectType) getGraphQLType(resourceType);
        if (graphQLType == null) {
            return null;
        }
        return buildSchema(graphQLType);
    }

    private static GraphQLSchema buildSchema(final GraphQLObjectType queryType) {
        final var codeRegistry = GraphQLCodeRegistry.newCodeRegistry()
                .defaultDataFetcher(new FhirGraphQLDataFetcherFactory<>())
                .build();

        return GraphQLSchema.newSchema()
                .query(queryType)
                .codeRegistry(codeRegistry)
                .build();
    }

    public static GraphQLOutputType getGraphQLType(final String resourceType) {
        if (resourceType.equals("Extension") ||
                resourceType.equals("ExampleScenario") ||
                resourceType.equals("GraphDefinition") ||
                resourceType.equals("QuestionnaireResponse") ||
                resourceType.equals("ResourceList")) {
            return null;
        }

        var result = typeCache.get(resourceType);
        if (result == null) {
            result = buildGraphQLType(resourceType);
            typeCache.put(resourceType, result);
        }

        return result;
    }

    private static GraphQLOutputType buildGraphQLType(final String resourceType) {
        if (resourceType == null) {
            return null;
        }

        final var schema = FhirSchema.getResourceTypeSchema(resourceType);
        if (schema == null) {
            return null;
        }

        final var builder = GraphQLObjectType.newObject();
        builder.name(resourceType);

        if (schema.containsKey("description")) {
            builder.description(schema.getString("description"));
        }

        final var properties = schema.getJsonObject("properties");
        if (properties == null) {
            return null;
        }

        for (final var entry : properties.entrySet()) {
            final var propertyName = entry.getKey();
            if (propertyName.startsWith("_") ||
                    propertyName.equals("contained") ||
                    propertyName.equals("extension") ||
                    propertyName.equals("modifierExtension") ||
                    propertyName.equals("resource") ||
                    (resourceType.equals("Reference") && propertyName.equals("identifier")) ||
                    (resourceType.equals("Bundle_Response") && propertyName.equals("outcome"))) {
                continue;
            }

            final var property = (JsonObject) entry.getValue();
            final var propertyType = getPropertyType(resourceType, property);
            if (propertyType == null) {
                LOG.warn("Missing property type for {}.{}", resourceType, propertyName);
                continue;
            }

            builder.field(GraphQLFieldDefinition.newFieldDefinition()
                    .name(propertyName)
                    .description(property.getString("description"))
                    .type(propertyType)
                    .build());
        }

        return builder.build();
    }

    private static GraphQLOutputType getPropertyType(final String parentType, final JsonObject property) {
        final var refStr = getRefString(property);
        if (refStr != null) {
            if (refStr.equals(parentType)) {
                return GraphQLTypeReference.typeRef(parentType);
            }
            return getGraphQLType(refStr);
        }

        if (property.containsKey("type")) {
            final var typeStr = property.getString("type");
            if (typeStr.equals("array")) {
                final var itemType = getPropertyType(parentType, property.getJsonObject("items"));
                if (itemType == null) {
                    return null;
                }
                return GraphQLList.list(itemType);
            }

            return getGraphQLType(typeStr);
        }

        if (property.containsKey("enum")) {
            return Scalars.GraphQLString;
        }

        if (property.containsKey("const")) {
            return Scalars.GraphQLString;
        }

        return null;
    }

    private static String getRefString(final JsonObject property) {
        if (!property.containsKey("$ref")) {
            return null;
        }

        return property.getString("$ref").replace("#/definitions/", "");
    }
}