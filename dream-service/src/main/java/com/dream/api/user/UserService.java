package com.dream.api.user;

import com.dream.dao.user.UserDAO;

public interface UserService {

    UserDAO getByUserId(String userId);

    String securePassword(String password);

    UserDAO addUser(UserDAO user);
}
