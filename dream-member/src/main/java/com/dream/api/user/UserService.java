package com.dream.api.user;

import com.dream.dao.user.UserDAO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {
    @Cacheable(value="users", key="'user_'+#userId")
    UserDAO getByUserId(@Param("userId") String userId);

    String securePassword(String password);

    UserDAO addUser(UserDAO user);
}
