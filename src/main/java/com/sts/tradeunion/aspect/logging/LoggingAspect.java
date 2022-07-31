package com.sts.tradeunion.aspect.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(public * com.sts.tradeunion.services.*.save(..))")
    public Object logAroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        Object targetMethodResult = joinPoint.proceed();
        return targetMethodResult;
    }

    @Around("execution(public * com.sts.tradeunion.services.*.findById(..))")
    public Object logAroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("START");
        Object targetMethodResult = joinPoint.proceed();
        logger.info("END");
        return targetMethodResult;
    }
}
