package com.gome.demo.http;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置
 *
 * @author xiehai1
 * @date 2017/03/14 18:26
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test300", "test500"})
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        // 如果有header
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(
                new ParameterBuilder()
                        .name("TOKEN")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build()
        );
        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(parameters)
                .apiInfo(this.apiInfo())
                .select()
                // controller的base包
                .apis(RequestHandlerSelectors.basePackage("com.gome.demo.http.controller"))
                // 忽略跟路径controller
                .paths(Predicates.not(PathSelectors.ant("/")))
                .build();
    }

    private ApiInfo apiInfo() {
//        Contact contact = new Contact("谢海", "https://github.com/PasseRR", "xiehai1@gome.com.cn");
        return new ApiInfoBuilder()
                .title("Demo For Swagger2")
//                .contact(contact)
                .version("1.0")
                .build();
    }
}
