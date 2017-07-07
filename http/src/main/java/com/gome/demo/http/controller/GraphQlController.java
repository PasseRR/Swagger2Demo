package com.gome.demo.http.controller;

import com.gome.demo.http.schema.GraphQlSchema;
import com.gome.demo.http.utils.GraphQlUtils;
import com.gome.demo.http.vo.HttpResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiehai1
 * @date 2017/03/14 14:15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@RestController
@RequestMapping("/graphQl")
// tags不能使用中文
@Api(tags = "GraphQlController", description = "人员相关接口")
public class GraphQlController {
    @Autowired
    private GraphQlSchema graphQlSchema;

    @ApiOperation(value = "人员信息api", notes = "add one person")
    @RequestMapping(method = RequestMethod.POST)
    public HttpResponseVo<Boolean> addPerson(@RequestBody String param) {
        return HttpResponseVo.ok(GraphQlUtils.execute(this.graphQlSchema.personSchema(), param));
    }
}
