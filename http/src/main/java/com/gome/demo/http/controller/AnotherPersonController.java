package com.gome.demo.http.controller;

import com.gome.demo.http.service.PersonService;
import com.gome.demo.http.vo.HttpResponseVo;
import com.gome.demo.http.vo.PersonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiehai1
 * @date 2017/03/14 14:15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@RestController
@RequestMapping("/anotherPerson")
@Api(tags = "AnotherPersonController", description = "人员相关接口2")
public class AnotherPersonController {
    @Autowired
    private PersonService personService;

    @ApiOperation(value = "添加一个人员信息", notes = "add one person")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpResponseVo addPerson(PersonVo personVo){
        return HttpResponseVo.ok(this.personService.addPerson(personVo));
    }

    @ApiOperation(value = "修改一个人员信息", notes = "modify one person")
    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public HttpResponseVo modifyPerson(PersonVo personVo){
        return HttpResponseVo.ok(this.personService.modifyPerson(personVo));
    }

    @ApiOperation(value = "获得指定id的人员信息", notes = "get person by id")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public HttpResponseVo getPerson(@ApiParam(name = "id", value = "人员id") @PathVariable int id){
        return HttpResponseVo.ok(this.personService.getPerson(id));
    }

    @ApiOperation(value = "获得所有人员信息", notes = "get all persons")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public HttpResponseVo getPerson(){
        return HttpResponseVo.ok(this.personService.getPerson());
    }
}
