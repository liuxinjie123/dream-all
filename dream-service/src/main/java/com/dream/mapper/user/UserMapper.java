package com.dream.mapper.user;

import com.dream.dao.user.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert(" INSERT INTO U_USER " +
            " (USER_ID, USERNAME, PASSWORD, STATUS, NAME, MOBILE, EMAIL, CREATE_TIME, CREATE_USER_ID, LAST_UPDATE_USER_ID) " +
            " VALUES " +
            " (#{userId}, #{username}, #{password}, #{status}, #{name}, #{mobile}, #{email}, now(), #{createUserId}, #{lastUpdateUserId}) ")
    @Options(useGeneratedKeys=true)
    int addUser(UserDAO user);

    @Select(" SELECT * FROM U_USER WHERE USER_ID=#{userId} ")
    UserDAO getByUserId(String userId);
}
