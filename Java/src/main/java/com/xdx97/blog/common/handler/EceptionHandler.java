package com.xdx97.blog.common.handler;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.websocket.SessionException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.SQLTransientConnectionException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class EceptionHandler {

    private Logger log = LoggerFactory.getLogger(EceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultObj<?> handle(Exception e) {
        log.error("已捕捉到异常！");
        ResultObj<?> bean = new ResultObj<>();
        Throwable ex = e;
        while(ex.getCause()!=null){
            ex = ex.getCause();
        }
        log.error("error info:" + getTrace(e));
        if (ex instanceof SessionException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            SessionException sessionException = (SessionException) ex;
            bean.setMessage(sessionException.getMessage());
            return bean;
        }else if (ex instanceof CommunicationsException ||ex instanceof SQLTransientConnectionException || ex instanceof UnknownHostException) {
            //数据库连接异常
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("目前系统网络较差，数据连接失败!");
            return bean;
        }else if (ex instanceof SocketTimeoutException) {
            //网络连接超时
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("网络连接超时,请刷新数据!");
            return bean;
        }else if (ex instanceof SocketException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("网络数据丢失,请刷新数据!");
            return bean;
        }else if (ex instanceof MysqlDataTruncation) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("字段数据长度超过限制！");
            return bean;
        }else if (ex instanceof MethodArgumentNotValidException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage(((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage());
            return bean;
        }else if (ex instanceof IllegalArgumentException) {
            bean.setCode(StatusCodeEnum.MISS_REQUIRED_PARAMS.getCode());
            bean.setMessage(ex.getMessage());
            return bean;
        } else if (ex instanceof RuntimeException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage(ex.getMessage());
            return bean;
        }else if (ex instanceof HttpRequestMethodNotSupportedException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("不支持该请求方式！");
            return bean;
        }else if (ex instanceof HttpMediaTypeNotSupportedException) {
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("不支持该请求参数类型！");
            return bean;
        }
        else {
            log.error("【系统异常】{}", e);
            bean.setCode(StatusCodeEnum.ERROR.getCode());
            bean.setMessage("系统错误,请联系管理员");
            return bean;
        }
    }
    /**
     * @desc 获取异常信息
     */
    public String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}

