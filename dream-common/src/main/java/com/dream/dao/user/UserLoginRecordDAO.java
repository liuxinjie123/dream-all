package com.dream.dao.user;

import com.dream.dao.BaseDAO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserLoginRecordDAO extends BaseDAO implements Serializable {
    private int id;
    private String userId;
    private String username;
    private String ip;
    private String acceptLanguage;
    private String userAgent;

    public UserLoginRecordDAO(String userId, String username, String ip, String acceptLanguage, String userAgent, String createUserId, String lastUpdateUserId) {
        super(createUserId, lastUpdateUserId);
        this.userId = userId;
        this.username = username;
        this.ip = ip;
        this.acceptLanguage = acceptLanguage;
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        if (userAgent == null || userAgent.length() < 190) {
            return userAgent;
        } else {
            return userAgent.substring(0, 190);
        }
    }

    public String getAcceptLanguage() {
        if (acceptLanguage == null || acceptLanguage.length() < 190) {
            return acceptLanguage;
        } else {
            return acceptLanguage.substring(0, 100);
        }
    }
}
