package com.gome.demo.http.utils;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

/**
 * @author xiehai1
 * @date 2017/07/07 16:03
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public final class GraphQlUtils {
    private GraphQlUtils() {

    }

    public static Object execute(GraphQLSchema schema, String param) {
        return GraphQL.newGraphQL(schema)
            .build()
            .execute(param)
            .getData();
    }
}
