package com.dream.api.user;

import com.dream.dao.user.UserDAO;
import com.dream.vo.Result;

public interface UserService {
    UserDAO getByUserId(String userId);

    UserDAO getByAccount(String account);

    Result addUser(UserDAO user);

    Result login(String username, String password);

    void logout(String token);

    Result queryUserByToken(String token);
}
