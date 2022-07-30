package com.sts.tradeunion.aspect.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    /**
     * Метод логгирует процесс сохранения Entity методом save в service слое.
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(public * com.sts.tradeunion.services.*.save(..))")
    public Object logAroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature.getMethod().getName()+ " some entity");
        Object targetMethodResult = joinPoint.proceed();
        System.out.println("End of " + methodSignature.getMethod().getName() + targetMethodResult.getClass());
        return targetMethodResult;
    }

    @Around("execution(public * com.sts.tradeunion.services.*.findById(..))")
    public Object logAroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature.getMethod().getName()+ " some entity");
        Object targetMethodResult = joinPoint.proceed();
        System.out.println("End of " + methodSignature.getMethod().getName() + targetMethodResult.getClass());
        return targetMethodResult;
    }
}
