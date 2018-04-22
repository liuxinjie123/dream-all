package com.dream.config;


import com.dream.dao.user.UserDAO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Session implements Serializable {
    protected UserDAO user;
    protected String picCode;
    protected String mailCode;  //邮箱验证码

    public boolean login(UserDAO user) {
        this.user = user;
        return true;
    }

    public boolean isLogined() {
        return this.user != null;
    }

    public void logout() {
        this.user = null;
    }


}
