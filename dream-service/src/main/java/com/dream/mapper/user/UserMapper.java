package com.dream.mapper.user;

import com.dream.dao.user.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert(" INSERT INTO U_USER " +
            " (USER_ID, USERNAME, PASSWORD, STATUS, NAME, MOBILE, EMAIL, CREATE_TIME, CREATE_USER_ID, LAST_UPDATE_USER_ID) " +
            " VALUES " +
            " (#{userId}, #{username}, #{password}, #{status}, #{name}, #{mobile}, #{email}, now(), #{createUserId}, #{lastUpdateUserId}) ")
    @Options(useGeneratedKeys=true)
    int addUser(UserDAO user);

    @Select(" SELECT USER_ID AS userId, USERNAME as username, PASSWORD AS password, STATUS as status, " +
            " NAME as name, MOBILE as mobile, EMAIL as email, CREATE_TIME AS createTime, CREATE_USER_ID AS createUserId, " +
            " LAST_UPDATE_USER_ID AS lastUpdateUserId, LAST_UPDATE_TIME AS lastUpdateTime " +
            " FROM U_USER WHERE USER_ID=#{userId} ")
    UserDAO getByUserId(String userId);
}
