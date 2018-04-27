package com.dream.mapper.log;

import com.dream.dao.log.ControllerMethodDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ControllerMethodMapper {

    @Insert(" INSERT INTO LOG_CONTROLLER_METHOD_RECORD " +
            " (USER_ID, METHOD_NAME, PARAMETER_COUNT, " +
            " CREATE_TIME, CREATE_USER_ID, LAST_UPDATE_USER_ID) " +
            " VALUES " +
            " (#{userId}, #{methodName}, #{parameterCount}, " +
            " now(), #{createUserId}, #{lastUpdateUserId}) ")
    @Options(useGeneratedKeys=true)
    int addRecord(ControllerMethodDao method);
}
