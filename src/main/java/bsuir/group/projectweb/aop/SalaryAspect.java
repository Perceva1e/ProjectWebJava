package bsuir.group.projectweb.aop;

import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
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
        Long id = null;
        if (methodSignature.getName().equals("deleteSalaryInAuthor")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long) {
                    id = (Long) arg;
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
        log.info("salary with id {} delete", id);
        return result;
    }

    @Around("PointCuts.saveMethodsSalary()")
    public Object aroundSaveAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Salary salary = null;
        if (methodSignature.getName().equals("saveSalary")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Salary) {
                    salary = (Salary) arg;
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
        assert salary != null;
        log.info("salary with price {} add", salary.getPrice());
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
                        price = (Integer) arg;
                        log.info("Try change salary price {}", price);
                        break;
                    }
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
