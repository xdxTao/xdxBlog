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
    MISS_REQUIRED_PARAMS(501,"缺少必要参数"),
    LOGIN_PARMAS_FAIL(502, "账号或密码错误"),
    LOGIN_INVALID(503, "登录失效，请重新登录"),
    ;

    private Integer code;
    private String name;
}
