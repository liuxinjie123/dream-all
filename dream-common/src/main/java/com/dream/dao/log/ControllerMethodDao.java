package com.dream.dao.log;

import com.dream.dao.BaseDAO;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControllerMethodDao extends BaseDAO implements Serializable {
    private int id;
    private String userId;
    private String methodName;
    private int parameterCount;

    public ControllerMethodDao(String methodName, int parameterCount) {
        this.methodName = methodName;
        this.parameterCount = parameterCount;
    }

    public ControllerMethodDao(String userId, String methodName, int parameterCount, String createUserId, String lastUpdateUserId) {
        super(createUserId, lastUpdateUserId);
        this.userId = userId;
        this.methodName = methodName;
        this.parameterCount = parameterCount;
    }
}
