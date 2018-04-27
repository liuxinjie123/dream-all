package com.dream.api.token;

import com.dream.dao.user.UserDAO;

public interface UserTokenService {
	UserDAO getUserByToken(String token);
    
}
