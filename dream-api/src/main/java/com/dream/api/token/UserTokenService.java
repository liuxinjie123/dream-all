package com.dream.api.token;

import com.dream.dao.user.UserDAO;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

public interface UserTokenService {
	UserDAO getUserByToken(String token);
    
}
