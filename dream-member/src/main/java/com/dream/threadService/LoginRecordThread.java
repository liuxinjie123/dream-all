package com.dream.threadService;

import com.dream.api.user.UserLoginRecordService;
import com.dream.dao.user.UserLoginRecordDAO;

public class LoginRecordThread extends Thread {
    private UserLoginRecordDAO loginRecord;
    private UserLoginRecordService loginRecordService;

    public LoginRecordThread(UserLoginRecordDAO loginRecord, UserLoginRecordService loginRecordService) {
        super();
        this.loginRecord = loginRecord;
        this.loginRecordService = loginRecordService;
    }

    @Override
    public void run() {
        loginRecordService.addUserLoginRecord(loginRecord);
    }
}
