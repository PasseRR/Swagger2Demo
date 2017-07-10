package com.gome.demo.http.vo;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

/**
 * @author xiehai1
 * @date 2017/07/10 22:11
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Data
public class OrderByVo extends BaseVo {
    @GraphQLQuery
    private String by;
    @GraphQLQuery
    private String order = "ASC";
}
