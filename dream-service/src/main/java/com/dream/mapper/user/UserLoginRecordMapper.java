package com.dream.mapper.user;

import com.dream.dao.log.ControllerMethodDao;
import com.dream.dao.user.UserLoginRecordDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UserLoginRecordMapper {

    @Insert(" INSERT INTO U_USER_LOGIN_RECORD " +
            " (USER_ID, USERNAME, IP, USER_AGENT, ACCEPT_LANGUAGE, " +
            " CREATE_TIME, CREATE_USER_ID, LAST_UPDATE_USER_ID) " +
            " VALUES " +
            " (#{userId}, #{username}, #{ip}, #{userAgent}, #{acceptLanguage}, " +
            " now(), #{createUserId}, #{lastUpdateUserId}) ")
    @Options(useGeneratedKeys=true)
    int addLoginRecord(UserLoginRecordDAO record);
}
