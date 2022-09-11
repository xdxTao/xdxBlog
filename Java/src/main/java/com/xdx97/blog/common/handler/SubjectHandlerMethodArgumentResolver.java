package com.xdx97.blog.common.handler;

import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.constant.SystemConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Configuration
public class SubjectHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        String parameterTypeName = parameterType.getName();
        String InformationVOName = InformationVO.class.getName();
        return InformationVOName.equalsIgnoreCase(parameterTypeName);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest requestNativeRequest = (HttpServletRequest)nativeWebRequest.getNativeRequest();
        Class<?> parameterType = methodParameter.getParameterType();
        String parameterTypeName = parameterType.getName();
        String InformationVOName = InformationVO.class.getName();
        if(InformationVOName.equalsIgnoreCase(parameterTypeName)){
            Object attribute = requestNativeRequest.getAttribute((SystemConstant.CUR_USER_INFO));
            if(Objects.isNull(attribute)){
                throw new RuntimeException("登录参数解析异常");
            }
            return attribute;
        }
        throw new RuntimeException("登录参数解析异常");
    }
}
