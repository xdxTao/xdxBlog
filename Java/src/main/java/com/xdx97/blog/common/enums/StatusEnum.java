package com.xdx97.blog.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

/**
 * 状态枚举
 */
@Getter
@ToString
public enum StatusEnum implements IBaseEnum<String, String, StatusEnum>{

    ENABLE("ENABLE", "启用"),
    DISABLE("DISABLE","禁用")
    ;

    private String code;
    private String name;


    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
        initMap(code, this);
    }

    @Override
    public String getMsg() {
        return this.name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @JsonCreator
    public static StatusEnum forValue(String code) {
        return IBaseEnum.get(StatusEnum.class, code);
    }
}
