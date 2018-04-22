package com.dream.interceptor;

import com.dream.annotation.Client;
import com.dream.dto.ClientInfo;
import com.dream.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;

@Service
public class ClientInfoMethodArgumentHandler implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(Client.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        return new ClientInfo(WebUtil.getIpAddress(request), request.getHeader("user-agent"), request.getHeader("accept-language"));
    }



}
