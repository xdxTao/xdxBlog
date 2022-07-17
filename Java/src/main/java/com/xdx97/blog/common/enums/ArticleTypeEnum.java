package com.xdx97.blog.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

/**
 * 文章类型
 */
@Getter
@ToString
public enum ArticleTypeEnum implements IBaseEnum<String, String, ArticleTypeEnum>{

    ORIGINAL("ORIGINAL", "原创"),
    REPRINT("REPRINT","转载"),
    TRANSLATE("TRANSLATE","翻译")
    ;

    private String code;
    private String name;


    ArticleTypeEnum(String code, String name) {
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
    public static ArticleTypeEnum forValue(String code) {
        return IBaseEnum.get(ArticleTypeEnum.class, code);
    }
}
