package com.gome.demo.http.schema;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.value.gson.GsonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author xiehai1
 * @date 2017/07/07 15:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public final class GraphQlSchemas {
    @Autowired
    private PersonSchema personSchema;

    private GraphQlSchemas() {

    }

    public Object executePersonSchema(String param) {
        ExecutionResult executionResult = GraphQL.newGraphQL(personSchema())
            .build()
            .execute(param);
        if (CollectionUtils.isEmpty(executionResult.getErrors())) {
            return executionResult.getData();
        } else {
            return executionResult.getErrors().get(0);
        }
    }

    private GraphQLSchema personSchema() {
        return new GraphQLSchemaGenerator()
            .withOperationsFromSingleton(this.personSchema)
            .withValueMapperFactory(new GsonValueMapperFactory())
            .withDefaults()
            .generate();
    }
}
