package com.dream.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.dream.api.user.UserLoginRecordService;
import com.dream.dao.user.UserLoginRecordDAO;
import com.dream.mapper.user.UserLoginRecordMapper;
import com.dream.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = Constants.DUBBO_VERSION)
public class UserLoginRecordServiceImpl implements UserLoginRecordService {
    @Autowired
    private UserLoginRecordMapper loginRecordMapper;

    @Override
    public void addUserLoginRecord(UserLoginRecordDAO loginRecord) {
        loginRecordMapper.addLoginRecord(loginRecord);
    }

}
