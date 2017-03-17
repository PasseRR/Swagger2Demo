package com.gome.demo.http.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiehai1
 * @date 2017/03/14 14:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PersonVo extends BaseVo {
    @ApiModelProperty("人员id")
    private int id;
    @ApiModelProperty("人员身份证号码")
    private String idCardNo;
    @ApiModelProperty("人员名字")
    private String name;
    @ApiModelProperty(value = "性别", allowableValues = "男, 女")
    private String sex;
}
