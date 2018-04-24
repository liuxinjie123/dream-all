package com.dream.member.api.user;

import com.dream.member.dao.user.UserDAO;
import com.dream.member.vo.Result;

public interface UserService {

    UserDAO getByUserId(String userId);

    UserDAO getByAccount(String account);

    Result addUser(UserDAO user);

    Result login(String username, String password);

    void logout(String token);

    Result queryUserByToken(String token);
}
