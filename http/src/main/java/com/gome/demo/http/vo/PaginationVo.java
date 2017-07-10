package com.gome.demo.http.vo;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

/**
 * @author xiehai1
 * @date 2017/07/10 22:12
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Data
public class PaginationVo extends BaseVo {
    @GraphQLQuery
    private Integer pageNumber;
    @GraphQLQuery
    private Integer pageSize;
}
