package com.gome.demo.http.schema;

import com.gome.demo.http.service.PersonService;
import com.gome.demo.http.vo.PersonVo;
import com.gome.demo.http.vo.QueryParamsVo;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiehai1
 * @date 2017/07/10 21:58
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class PersonSchema {
    @Autowired
    private PersonService personService;

    @GraphQLMutation
    // mutation{addPerson(person:{name:"张三",idCardNo:"111",sex:"女1"})}
    public Boolean addPerson(@GraphQLArgument(name = "person") PersonVo personVo) {
        return Boolean.valueOf(this.personService.addPerson(personVo));
    }

    @GraphQLMutation
    // mutation{updatePerson(person:{id:1,name:"张三",idCardNo:"111",sex:"女"})}
    public Boolean updatePerson(@GraphQLArgument(name = "person") PersonVo personVo){
        return Boolean.valueOf(this.personService.modifyPerson(personVo));
    }

    @GraphQLMutation
    // e.g. mutation {deletePerson(id:1)}
    // 调用两次delete
    // e.g. mutation {first:deletePerson(id:3) second:deletePerson(id:4)}
    public Boolean deletePerson(@GraphQLArgument(name = "id") Integer id){
        return Boolean.valueOf(this.personService.deletePerson(id));
    }

    @GraphQLQuery
    // {getPerson(id:1){id, name}}
    public PersonVo getPerson(@GraphQLArgument(name = "id") Integer id){
        return this.personService.getPerson(id);
    }

    @GraphQLQuery
    // {getPersons{id, name}}
    public List<PersonVo> getPersons(){
        return this.personService.getPersons();
    }

    @GraphQLQuery
    public List<PersonVo> queryPersons(@GraphQLArgument(name = "param")QueryParamsVo<PersonVo> paramsVo){
        return this.personService.queryPersons(paramsVo);
    }
}
