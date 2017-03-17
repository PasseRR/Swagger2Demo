package com.gome.demo.http.controller;

import com.gome.demo.http.service.PersonService;
import com.gome.demo.http.vo.HttpResponseVo;
import com.gome.demo.http.vo.PersonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiehai1
 * @date 2017/03/14 14:15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@RestController
@RequestMapping("/person")
// tags不能使用中文
@Api(tags = "PersonController", description = "人员相关接口")
public class PersonController {
    @Autowired
    private PersonService personService;

    @ApiOperation(value = "添加一个人员信息", notes = "add one person")
    @RequestMapping(method = RequestMethod.POST)
    public HttpResponseVo<Boolean> addPerson(@RequestBody PersonVo personVo){
        return HttpResponseVo.ok(this.personService.addPerson(personVo));
    }

    @ApiOperation(value = "修改一个人员信息", notes = "modify one person")
    @RequestMapping(method = RequestMethod.PUT)
    public HttpResponseVo<Boolean> modifyPerson(@RequestBody PersonVo personVo){
        return HttpResponseVo.ok(this.personService.modifyPerson(personVo));
    }

    @ApiOperation(value = "删除一个人员信息", notes = "modify one person")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpResponseVo<Boolean> deletePerson(@ApiParam(name = "id", value = "人员id") @PathVariable int id){
        return HttpResponseVo.ok(this.personService.deletePerson(id));
    }

    @ApiOperation(value = "获得指定id的人员信息", notes = "get person by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpResponseVo<PersonVo> getPerson(@ApiParam(name = "id", value = "人员id") @PathVariable int id){
        return HttpResponseVo.ok(this.personService.getPerson(id));
    }

    @ApiOperation(value = "获得所有人员信息", notes = "get all persons")
    @RequestMapping(method = RequestMethod.GET)
    public HttpResponseVo<List<PersonVo>> getPerson(){
        return HttpResponseVo.ok(this.personService.getPerson());
    }
}
