package com.dream.service.api.user;

import com.dream.service.dao.user.UserDAO;

public interface UserService {
    UserDAO getByUserId(String userId);
}
