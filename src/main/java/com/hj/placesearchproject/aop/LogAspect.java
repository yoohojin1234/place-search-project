package com.hj.placesearchproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.hj.placesearchproject.controller.*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("========== [start ] - class[{}] / method[{}]", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("========== [finish] - class[{}] / method[{}] / time[{} sec]", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), (System.currentTimeMillis() - startTime) / 1000);
        return result;
    }


}