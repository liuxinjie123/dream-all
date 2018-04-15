package com.dream.api.user;

import com.dream.dao.user.UserDAO;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {
    @Cacheable(value="users", key="'user_'+#userId")
    UserDAO getByUserId(String userId);

    String securePassword(String password);

    UserDAO addUser(UserDAO user);
}
