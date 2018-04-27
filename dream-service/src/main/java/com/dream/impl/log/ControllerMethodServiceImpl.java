package com.dream.impl.log;

import com.alibaba.dubbo.config.annotation.Service;
import com.dream.api.log.ControllerMethodService;
import com.dream.dao.log.ControllerMethodDao;
import com.dream.mapper.log.ControllerMethodMapper;
import com.dream.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = Constants.DUBBO_VERSION)
public class ControllerMethodServiceImpl implements ControllerMethodService {
    @Autowired
    private ControllerMethodMapper controllerMethodMapper;

    @Override
    public void addLog(ControllerMethodDao method) {
        controllerMethodMapper.addRecord(method);
    }
}
