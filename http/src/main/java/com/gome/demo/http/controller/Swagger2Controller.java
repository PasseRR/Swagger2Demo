package com.gome.demo.http.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xiehai1
 * @date 2017/03/17 09:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller("redirectSwagger2Controller")
@Api(hidden = true)
public class Swagger2Controller {
    /**
     * 默认跟路径重定向
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectSwagger(){
        return "redirect:swagger-ui.html";
    }
}
