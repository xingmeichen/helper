package com.mabel.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-11-24 16:28
 **/
@Aspect
@Component
public class UserAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAspect.class);

    @Pointcut("execution(public * com.mabel.controller.UserController.test(..))")
    @Before("test()")
    public void loginLog() {
        //TODO : to add login log,
        // 可能是哪里配置还有问题，这个切点没有生效
        LOGGER.info("add login log");
    }
}