package com.cts.smartspend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Pointcut("execution(* com.cts.smartspend.controller.*.*(..))")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void beforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Executing method: {}", methodName);
    }

//    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
//    public void afterControllerReturn(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("Method: {} executed successfully.", methodName);
//    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void afterControllerThrow(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Method: {} executed with error: {}", methodName, ex.getMessage());
    }

}
