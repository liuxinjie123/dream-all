package com.dream.threadService;

import com.dream.api.log.ControllerMethodService;
import com.dream.dao.log.ControllerMethodDao;

public class ActionThread extends Thread {
    private ControllerMethodService methodService;
    private ControllerMethodDao methodDao;

    public ActionThread(ControllerMethodDao methodDao, ControllerMethodService methodService) {
        super();
        this.methodDao = methodDao;
        this.methodService = methodService;
    }

    @Override
    public void run() {
        synchronized (methodDao) {
            methodService.addLog(methodDao);
        }
    }
}
