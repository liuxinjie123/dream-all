package com.dream.member.api.token;

import com.dream.member.dao.user.UserDAO;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:redis.properties")
public interface UserTokenService {
	UserDAO getUserByToken(String token);
    
}
