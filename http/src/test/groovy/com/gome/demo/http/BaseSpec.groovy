package com.gome.demo.http

import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = DemoApplication.class)
class BaseSpec extends Specification{

}
