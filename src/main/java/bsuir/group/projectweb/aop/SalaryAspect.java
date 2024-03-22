package bsuir.group.projectweb.aop;

import bsuir.group.projectweb.model.Salary;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SalaryAspect {
    @Around("PointCuts.deleteMethodsSalary()")
    public Object aroundDeleteAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Long ids = null;
        if (methodSignature.getName().equals("deleteSalaryInAuthor")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long id) {
                    ids = id;
                    log.info("Try delete salary with id {}", id);
                }
            }
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("salary with id {} delete", ids);
        return result;
    }

    @Around("PointCuts.saveMethodsSalary()")
    public Object aroundSaveAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Salary salaries = null;
        if (methodSignature.getName().equals("saveSalary")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Salary salary) {
                    salaries = salary;
                    log.info("Try add salary with price {}", salary.getPrice());
                }
            }
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        assert salaries != null;
        log.info("salary with price {} add", salaries.getPrice());
        return result;
    }

    @Around("PointCuts.changeMethodsSalary()")
    public Object aroundChangeAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Long id;
        Integer price;
        int countForArgs = 0;
        if (methodSignature.getName().equals("changeSalaryById")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long || arg instanceof Integer) {
                    if (countForArgs == 1) {
                        assert arg instanceof Integer;
                        price = (Integer) arg;
                        log.info("Try change salary price {}", price);
                        break;
                    }
                    assert arg instanceof Long;
                    id = (Long) arg;
                    countForArgs++;
                    log.info("Try change author with id {}", id);
                }
            }
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Method change salary");
        return result;
    }
}
