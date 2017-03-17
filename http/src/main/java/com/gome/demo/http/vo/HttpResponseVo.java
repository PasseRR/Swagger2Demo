package com.gome.demo.http.vo;

import lombok.Builder;
import lombok.Getter;

/**
 * @author xiehai1
 * @date 2017/03/14 14:41
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Builder
@Getter
public class HttpResponseVo<T> extends BaseVo {
    private String code;
    private String msg;
    private T data;

    public static <T> HttpResponseVo ok(T data) {
        return HttpResponseVo.builder()
                .code("00")
                .data(data)
                .build();
    }

    public static HttpResponseVo fail(String code, String msg) {
        return HttpResponseVo.builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
