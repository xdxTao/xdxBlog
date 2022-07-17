package com.xdx97.blog.bean;

import com.xdx97.blog.common.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultObj<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultObj success() {

        return ResultObj.builder()
                .code(StatusCodeEnum.OK.getCode())
                .message(StatusCodeEnum.OK.getName())
                .build();
    }

    public static ResultObj success(Object obj) {

        return ResultObj.builder()
                .code(StatusCodeEnum.OK.getCode())
                .message(StatusCodeEnum.OK.getName())
                .data(obj)
                .build();
    }

    public static ResultObj fail() {

        return ResultObj.builder()
                .code(StatusCodeEnum.ERROR.getCode())
                .message(StatusCodeEnum.ERROR.getName())
                .build();
    }

    public static ResultObj fail(String message) {

        return ResultObj.builder()
                .code(StatusCodeEnum.ERROR.getCode())
                .message(message)
                .build();
    }




}
