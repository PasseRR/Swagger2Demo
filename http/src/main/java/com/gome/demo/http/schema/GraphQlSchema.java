package com.gome.demo.http.schema;

import com.gome.demo.http.schema.field.PersonFields;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static graphql.schema.GraphQLObjectType.newObject;

/**
 * @author xiehai1
 * @date 2017/07/07 15:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class GraphQlSchema {
    @Autowired
    private PersonFields personFields;

    @Bean
    public GraphQLSchema personSchema() {
        return GraphQLSchema.newSchema()
            .query(
                newObject().name("personQueryType")
                    .field(this.personFields.get())
                    .field(this.personFields.getAllPersons())
                    .build()
            ).mutation(
                newObject().name("personMutationType")
                    .field(this.personFields.add())
                    .field(this.personFields.update())
                    .field(this.personFields.delete())
                    .build()
            ).build();
    }
}
