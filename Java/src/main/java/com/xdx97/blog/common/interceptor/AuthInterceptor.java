package com.xdx97.blog.common.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.annotation.SkipLoginAuth;
import com.xdx97.blog.common.constant.SystemConstant;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(handler instanceof HandlerMethod && !HttpMethod.OPTIONS.matches(request.getMethod().toUpperCase())){
            HandlerMethod method = (HandlerMethod) handler;
            String xdxToken = request.getHeader("XDX-TOKEN");

            SkipLoginAuth skipLoginAuth = method.getMethodAnnotation(SkipLoginAuth.class);
            if (null == skipLoginAuth) {
                if(StringUtils.isEmpty(xdxToken)){
                    throw new BizException(StatusCodeEnum.LOGIN_INVALID);
                }
                JSONObject payloads = JWTUtil.parseToken(xdxToken).getPayloads();
                if (System.currentTimeMillis() > payloads.getLong("expireDate")) {
                    throw new BizException(StatusCodeEnum.LOGIN_INVALID);
                }
                InformationVO informationVO = payloads.get("data", InformationVO.class);
                if(null == informationVO){
                    throw new BizException(StatusCodeEnum.LOGIN_INVALID);
                }
                request.setAttribute(SystemConstant.CUR_USER_INFO, informationVO);
                return true;
            }
        }
        return true;
    }
}
