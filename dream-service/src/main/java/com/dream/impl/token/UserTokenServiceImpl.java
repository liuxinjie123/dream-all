package com.dream.impl.token;

import com.dream.api.token.UserTokenService;
import com.dream.dao.user.UserDAO;
import com.dream.utils.HttpClientUtil;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:redis.properties")
public class UserTokenServiceImpl implements UserTokenService {
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_DOMAIN_BASE_USRL}")
    public String SSO_DOMAIN_BASE_USRL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;


    @Override
    public UserDAO getUserByToken(String token) {
        try {
            // 调用sso系统的服务，根据token取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            System.out.println("json : " + json);
            // 把json转换成 Result
            Result result = Result.formatToPojo(json, UserDAO.class);
            if (null != result && Constants.CODE_200.equals(result.getCode())) {
                UserDAO user = (UserDAO) result.getData();
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
