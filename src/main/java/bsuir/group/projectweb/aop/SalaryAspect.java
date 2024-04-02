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
    /**
     * This is aspect of all salary method delete.
     *
     * @param joinPoint point of enter
     * @return restore object
     */
    @Around("PointCuts.deleteMethodsSalary()")
    public Object aroundDeleteAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint,
                "deleteSalaryInAuthor",
                "Try delete salary with id {}");
    }

    /**
     * This is aspect of all salary method save.
     *
     * @param joinPoint point of enter
     * @return restore object
     */
    @Around("PointCuts.saveMethodsSalary()")
    public Object aroundSaveAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint,
                "saveSalary",
                "Try add salary with price {}");
    }

    /**
     * This is aspect of all salary method change.
     *
     * @param joinPoint point of enter
     * @return restore object
     */
    @Around("PointCuts.changeMethodsSalary()")
    public Object aroundChangeAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint,
                "changeSalaryByIdInAuthor",
                "Try change salary price {}");
    }

    /**
     * This is method of process.
     *
     * @param joinPoint  point of enter
     * @param logMessage log message
     * @param methodName name method for
     * @return restore result
     */
    private Object processMethod(final ProceedingJoinPoint joinPoint,
                                 final String methodName,
                                 final String logMessage) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        Object result;
        try {
            if (methodSignature.getName().equals(methodName)) {
                Object[] arguments = joinPoint.getArgs();
                for (Object arg : arguments) {
                    if (arg instanceof Salary salary) {
                        log.info(logMessage, salary.getPrice());
                    }
                }
            }
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Method {} processed", methodName);
        return result;
    }
}
