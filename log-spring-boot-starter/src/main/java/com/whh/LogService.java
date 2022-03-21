package com.whh;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Aspect
public class LogService {
    private LogProperties logProperties;

    public LogService(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Pointcut("@annotation(com.whh.AutoLog)")
    private void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(autoLog)",argNames = "joinPoint,autoLog")
    public Object  advice( ProceedingJoinPoint joinPoint,AutoLog autoLog) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if(args!=null&&args.length>0){
            log.info(joinPoint.getSignature().getDeclaringTypeName()+":"+ Arrays.stream(args).map(s->s!=null?s.toString():"").collect(Collectors.joining(",")));
        }else{
            log.info(joinPoint.getSignature().getDeclaringTypeName());
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("LogService.advice", e);
            throw e;
        }
        log.info(joinPoint.getSignature().getDeclaringTypeName()+":"+result);
        return result;
    }

}
