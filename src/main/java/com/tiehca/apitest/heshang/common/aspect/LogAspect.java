package com.tiehca.apitest.heshang.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("within(com.tiehca.apitest.heshang.controller.*)")
    public void login (JoinPoint joinPoint) {
        //TODO
    }
}
