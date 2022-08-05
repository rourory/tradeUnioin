package com.sts.tradeunion.aspect.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(public * com.sts.tradeunion.controllers.*.save(..))")
    public Object loggingAroundSaveControllerMethods(ProceedingJoinPoint joinPoint)throws Throwable{
        logger.info("Попытка пользователя {} добавить новые данные о {}",
                getInfoAboutUser(),
                joinPoint.getSignature().toString());
        Object targetMethodResult = joinPoint.proceed();
        logger.info("Данные успешно добавлены пользоваетелем {}",
                getInfoAboutUser());
        return targetMethodResult;
    }

    @Around("execution(public * com.sts.tradeunion.controllers.*.update(..))")
    public Object loggingAroundUpdateControllerMethods(ProceedingJoinPoint joinPoint)throws Throwable{
        logger.info("Попытка пользователя {} изменить данные о {}",
                getInfoAboutUser(),
                joinPoint.getSignature().toString());
        Object targetMethodResult = joinPoint.proceed();
        logger.info("Данные успешно изменены пользоваетелем {}",
                getInfoAboutUser());
        return targetMethodResult;
    }
    @Around("execution(public * com.sts.tradeunion.controllers.*.delete(..))")
    public Object loggingAroundDeleteControllerMethods(ProceedingJoinPoint joinPoint)throws Throwable{
        logger.info("Попытка пользователя {} удалить данные о {}",
                getInfoAboutUser(),
                joinPoint.getSignature().toString());
        Object targetMethodResult = joinPoint.proceed();
        logger.info("Данные успешно удалены пользоваетелем {}",
                getInfoAboutUser());
        return targetMethodResult;
    }
    @Around("execution(public * com.sts.tradeunion.controllers.*.get(..))")
    public Object loggingAroundGetControllerMethods(ProceedingJoinPoint joinPoint)throws Throwable{
        logger.info("Попытка пользователя {} получить данные о {}",
                getInfoAboutUser(),
                joinPoint.getSignature().toString());
        Object targetMethodResult = joinPoint.proceed();
        logger.info("Данные успешно получены пользоваетелем {}",
                getInfoAboutUser());
        return targetMethodResult;
    }

    @Around("execution(public * com.sts.tradeunion.controllers.*.getByOwner(..))")
    public Object loggingAroundGetByOwnerControllerMethods(ProceedingJoinPoint joinPoint)throws Throwable{
        logger.info("Попытка пользователя {} получить данные о {}",
                getInfoAboutUser(), joinPoint.getSignature().toString());
        Object targetMethodResult = joinPoint.proceed();
        logger.info("Данные успешно получены пользоваетелем {}",
                getInfoAboutUser());
        return targetMethodResult;
    }

    /**
     * Метод возвращает имя пользователя, хранящееся в SecurityContextHolder приложения
     * @return имя пользователя
     */
    private String getInfoAboutUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

}
