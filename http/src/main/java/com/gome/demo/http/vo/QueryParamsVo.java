package com.gome.demo.http.vo;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

/**
 * @author xiehai1
 * @date 2017/07/10 22:13
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Data
public class QueryParamsVo<T> extends BaseVo {
    @GraphQLQuery
    private T params;
    @GraphQLQuery(name = "orderBy")
    private OrderByVo orderByVo;
    @GraphQLQuery(name = "page")
    private PaginationVo paginationVo;
}
