package com.gome.demo.http.vo;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author xiehai1
 * @date 2017/03/14 14:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Data
@AllArgsConstructor
@Builder
@ApiModel
public class PersonVo extends BaseVo {
    @ApiModelProperty("人员id")
    @GraphQLQuery
    private int id;
    @ApiModelProperty("人员身份证号码")
    @GraphQLQuery
    private String idCardNo;
    @ApiModelProperty("人员名字")
    @GraphQLQuery
    private String name;
    @ApiModelProperty(value = "性别", allowableValues = "男, 女")
    @GraphQLQuery
    private String sex;

    public PersonVo(){

    }
}
