package com.xdx97.blog.common.exception;

import com.xdx97.blog.common.enums.StatusCodeEnum;
import lombok.Data;

/**
 * 系统异常
 */
@Data
public class BizException extends RuntimeException{

    private Integer errCode;

    private String errMsg;

    public BizException(Integer errCode, String errMsg, Exception e){
        super(e);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(Integer errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(StatusCodeEnum statusCodeEnum){
        this.errCode = statusCodeEnum.getCode();
        this.errMsg = statusCodeEnum.getName();
    }
}

