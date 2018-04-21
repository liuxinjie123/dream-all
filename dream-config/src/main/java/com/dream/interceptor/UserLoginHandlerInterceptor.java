package com.dream.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.api.token.UserTokenService;
import com.dream.dao.user.UserDAO;
import com.dream.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Service
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "USER_TOKEN";

    @Autowired
    private UserTokenService userService;
    @Value("${loginPageUrl}")
    private String loginPageUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(" cookie name=" + COOKIE_NAME);
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        System.out.println(" token=" + token);
        UserDAO user = userService.getUserByToken(token);
        System.out.println(" user=" + (user == null ? "null" : user.toString()));
        if (StringUtils.isBlank(token) || null == user) {
			// 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect(loginPageUrl + "?redirect=" + request.getRequestURL());
			// 返回false
			return false;
		}
		// 把用户信息放入Request
		request.setAttribute("user", user);
		// 返回值决定handler是否执行。true：执行，false：不执行。
		return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
    }

}