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
    public Object aroundDeleteAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint, "deleteSalaryInAuthor", "Try delete salary with id {}");
    }

    @Around("PointCuts.saveMethodsSalary()")
    public Object aroundSaveAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint, "saveSalary", "Try add salary with price {}");
    }

    @Around("PointCuts.changeMethodsSalary()")
    public Object aroundChangeAdvice(final ProceedingJoinPoint joinPoint) {
        return processMethod(joinPoint, "changeSalaryByIdInAuthor", "Try change salary price {}");
    }

    private Object processMethod(ProceedingJoinPoint joinPoint, String methodName, String logMessage) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
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
