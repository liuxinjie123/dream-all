package com.dream.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dream.api.redis.JedisClient;
import com.dream.api.token.UserTokenService;
import com.dream.dao.user.UserDAO;
import com.dream.utils.CookieUtils;
import com.dream.utils.SecureUtil;
import com.dream.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Service
public class UserLoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private UserTokenService userService;
    @Autowired
    private JedisClient jedisClient;
    @Value("${USER_TOKEN_NAME_KEY}")
    private String USER_TOKEN_NAME;
    @Value("${REDIS_USER_SESSION_INFO_KEY}")
    private String REDIS_USER_SESSION_INFO_KEY;
    @Value("${loginPageUrl}")
    private String loginPageUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(" --- 111 ---, cookie name=" + USER_TOKEN_NAME);
        // 从cookie中取得token
        String token = CookieUtils.getCookieValue(request, USER_TOKEN_NAME);
        System.out.println(" --- 222 ---, token=" + token);
        if (StringUtils.isBlank(token)) {
            // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect(loginPageUrl + "?redirect=" + request.getRequestURL());
            // 返回false
            return false;
        }
        System.out.println(" --- 333 ---, token is not null");
        // 校验客户登录ip等信息
        String ip = WebUtil.getIpAddress(request);
        String userAgent = request.getHeader("user-agent");
        String acceptLanguage = request.getHeader("accept-language");
        String clientInfoMsg = ip + userAgent + acceptLanguage;
        System.out.println(" --- 333 ---, clientInfoMsg=" + clientInfoMsg);
        String clientInfoMsgToken = SecureUtil.securePassword(clientInfoMsg);
        String cacheClientInfoMsgToken = jedisClient.get(REDIS_USER_SESSION_INFO_KEY + ":" + token);
        System.out.println(" --- 444 ---, clientInfoMsgToken = " + clientInfoMsgToken);
        System.out.println(" --- 555 ---, cacheClientInfoMsgToken = " + cacheClientInfoMsgToken);
        if (StringUtils.isBlank(cacheClientInfoMsgToken) || !clientInfoMsgToken.equals(cacheClientInfoMsgToken)) {
            // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect(loginPageUrl + "?redirect=" + request.getRequestURL());
            // 返回false
            return false;
        }
        System.out.println(" --- 666 ---, cacheClientInfoMsgToken is valid. ");
        // 从缓存中取 user 信息
        UserDAO user = userService.getUserByToken(token);
        System.out.println(" --- 777 ---, user=" + (user == null ? "null" : user.toString()));
        if (null == user) {
			// 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect(loginPageUrl + "?redirect=" + request.getRequestURL());
			// 返回false
			return false;
		}
        System.out.println(" --- 888 ---, user is valid.");
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