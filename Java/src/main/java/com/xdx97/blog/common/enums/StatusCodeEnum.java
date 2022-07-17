package com.xdx97.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态code枚举
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    OK(200, "正常"),
    ERROR(500,"系统异常"),
    MISS_REQUIRED_PARAMS(501,"缺少必要参数")
    ;

    private Integer code;
    private String name;
}
