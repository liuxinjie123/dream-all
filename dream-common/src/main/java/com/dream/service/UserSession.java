package com.dream.service;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSession {

    private User user;

    private String imgCode;

    public boolean login(User user) {
        this.user = user;
        return true;
    }

    public void logout() {
        this.user = null;
    }

    public boolean isLogined() {
        return this.user != null && this.user.getUserId() != null;
    }

    public void removeImgCode() {
        this.imgCode = null;
    }

}
