package com.dream.service.impl.user;

import com.dream.service.api.user.UserService;
import com.dream.service.dao.user.UserDAO;
import org.springframework.stereotype.Service;

@Service("user.UserService")
public class UserServiceImpl implements UserService {
    @Override
    public UserDAO getByUserId(String userId) {
        if ("1".equals(userId)) {
            return new UserDAO("刘新杰", "15618177577");
        } else {
            return new UserDAO();
        }
    }
}
