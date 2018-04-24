package com.dream.member.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WebUtil {

    /**
     * 获取IP方法
     */
    public static String getIpAddress(HttpServletRequest request) {
        String IP = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(IP) && !"unKnown".equalsIgnoreCase(IP)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = IP.indexOf(",");
            if(index != -1) {
                return IP.substring(0, index);
            } else {
                return IP;
            }
        }
        IP = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(IP) && !"unKnown".equalsIgnoreCase(IP)){
            return IP;
        }
        return request.getRemoteAddr();
    }



}
