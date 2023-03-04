package com.kalidratorma.cms.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* com.kalidratorma.cms.core.site.SiteController.*(..))")
    public Object aroundAllSiteControllerMethodAdvice(
            ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Logger logger = LoggerFactory.getLogger(methodSignature.getClass());
        List<String> params = List.of(methodSignature.getParameterNames());
        StringBuffer paramsStringBuffer = new StringBuffer("");
        params.stream().forEach(s -> paramsStringBuffer.append(s).append("=[{}],"));
        logger.info(methodName + " called with params "
                + paramsStringBuffer.deleteCharAt(paramsStringBuffer.length()-1),
                joinPoint.getArgs());
        Object targetMethodResult = joinPoint.proceed();
        return targetMethodResult;
    }
}
