package com.dream.aspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.annotation.Action;
import com.dream.api.log.ControllerMethodService;
import com.dream.dao.log.ControllerMethodDao;
import com.dream.dao.user.UserDAO;
import com.dream.dto.Session;
import com.dream.threadService.ActionThread;
import com.dream.vo.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

@Aspect
@Component
public class ActionAspect {
    @Autowired
    private Session session;
    @Autowired
    private ExecutorService executorService;
    @Reference(version = Constants.DUBBO_VERSION)
    private ControllerMethodService methodService;


    @Pointcut("@annotation(com.dream.annotation.Action)")
    public void annotationPointCut() {}

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截: " + action.name());
    }

    @Before("execution(* com.dream.controller.*.*.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        UserDAO user = session.getUser();
        ControllerMethodDao methodDao = null;
        if (user != null) {
            methodDao = new ControllerMethodDao(user.getUserId(), method.getName(), method.getParameterCount(),
                    user.getUserId(), user.getUserId());
        } else {
            methodDao = new ControllerMethodDao(method.getName(), method.getParameterCount());
        }
        ActionThread thread = new ActionThread(methodDao, methodService);
        executorService.execute(thread);
        System.out.println("方法式拦截: " + method.getName());
    }

}
