package com.gome.demo.http.schema.field;

import com.gome.demo.http.service.PersonService;
import com.gome.demo.http.vo.PersonVo;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLObjectType.newObject;


/**
 * @author xiehai1
 * @date 2017/07/07 15:34
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class PersonFields {
    @Autowired
    private PersonService personService;
    private GraphQLOutputType personOutputType = newObject().name("OutputPerson")
        .field(newFieldDefinition().name("id").type(GraphQLInt).build())
        .field(newFieldDefinition().name("idCardNo").type(GraphQLString).build())
        .field(newFieldDefinition().name("name").type(GraphQLString).build())
        .field(newFieldDefinition().name("sex").type(GraphQLString).build())
        .build();
    private GraphQLInputType personInputType = newInputObject().name("InputPerson")
        .field(newInputObjectField().name("id").type(GraphQLInt).build())
        .field(newInputObjectField().name("idCardNo").type(GraphQLString).build())
        .field(newInputObjectField().name("name").type(GraphQLString).build())
        .field(newInputObjectField().name("sex").type(GraphQLString).build())
        .build();

    // e.g. {person(id:1){id, name}}
    public GraphQLFieldDefinition get() {
        return GraphQLFieldDefinition.newFieldDefinition()
            .name("person")
            .argument(newArgument().name("id").type(GraphQLInt).build())
            .type(personOutputType)
            .dataFetcher(env -> {
                Integer id = env.getArgument("id");
                return personService.getPerson(id.intValue());
            }).build();
    }

    // e.g. {persons{id, name}}
    public GraphQLFieldDefinition getAllPersons() {
        return GraphQLFieldDefinition.newFieldDefinition()
            .name("persons")
            .type(new GraphQLList(personOutputType))
            .dataFetcher(env -> this.personService.getPersons())
            .build();
    }

    // e.g. mutation{addPerson(person:{name:"张三",idCardNo:"111",sex:"女"})}
    public GraphQLFieldDefinition add() {
        return GraphQLFieldDefinition.newFieldDefinition()
            .name("addPerson")
            .argument(newArgument().name("person").type(personInputType))
            .type(GraphQLBoolean)
            .dataFetcher(env -> {
                Map<String, Object> person = env.getArgument("person");
                return this.personService.addPerson(
                    PersonVo.builder()
                        .name(String.valueOf(person.get("name")))
                        .idCardNo(String.valueOf(person.get("idCardNo")))
                        .sex(String.valueOf(person.get("sex")))
                        .build()
                );
            }).build();
    }

    // e.g. mutation{updatePerson(person:{id:1,name:"张三",idCardNo:"111",sex:"女"})}
    public GraphQLFieldDefinition update() {
        return GraphQLFieldDefinition.newFieldDefinition()
            .name("updatePerson")
            .argument(newArgument().name("person").type(personInputType))
            .type(GraphQLBoolean)
            .dataFetcher(env -> {
                Map<String, Object> person = env.getArgument("person");
                return this.personService.modifyPerson(
                    PersonVo.builder()
                        .id(Integer.parseInt(String.valueOf(person.get("id"))))
                        .name(String.valueOf(person.get("name")))
                        .idCardNo(String.valueOf(person.get("idCardNo")))
                        .sex(String.valueOf(person.get("sex")))
                        .build()
                );
            }).build();
    }

    // e.g. mutation {deletePerson(id:1)}
    // 调用两次delete
    // e.g. mutation {first:deletePerson(id:3) second:deletePerson(id:4)}
    public GraphQLFieldDefinition delete() {
        return GraphQLFieldDefinition.newFieldDefinition()
            .name("deletePerson")
            .argument(newArgument().name("id").type(GraphQLInt))
            .type(GraphQLBoolean)
            .dataFetcher(env -> this.personService.deletePerson(env.getArgument("id")))
            .build();
    }
}