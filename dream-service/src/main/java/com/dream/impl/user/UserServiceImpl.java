package com.dream.impl.user;

import com.dream.mapper.user.UserMapper;
import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("user.UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDAO getByUserId(String userId) {
        UserDAO user = userMapper.getByUserId(userId);
        return user == null ? new UserDAO() : user;
    }

    @Override
    public String securePassword(String password) {
        return DigestUtils.md5Hex("$&*" + DigestUtils.md5Hex("@." + password + "$*************") + "!@#%……&");
    }

    @Override
    public UserDAO addUser(UserDAO user) {
        userMapper.addUser(user);
        return getByUserId(user.getUserId());
    }
}
