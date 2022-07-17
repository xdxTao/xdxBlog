package com.xdx97.blog.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

/**
 * 是否枚举
 */
@Getter
@ToString
public enum YesOrNoEnum implements IBaseEnum<String, String, YesOrNoEnum>{

    YES("YES", "是"),
    NO("NO","否")
    ;

    private String code;
    private String name;


    YesOrNoEnum(String code, String name) {
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
    public static YesOrNoEnum forValue(String code) {
        return IBaseEnum.get(YesOrNoEnum.class, code);
    }
}
