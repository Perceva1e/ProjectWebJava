package bsuir.group.projectweb.aop;

import bsuir.group.projectweb.dto.BulkTextRequestDTO;
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
public class TextAspect {
    /**
     * This is aspect of all text method delete.
     * @param joinPointDelete  point of enter
     * @return restore object
     */
    @Around("PointCuts.deleteMethodsText()")
    public Object aroundDeleteAdvice(
            final ProceedingJoinPoint joinPointDelete) {
        return processMethod(joinPointDelete,
                "deleteAuthorInText",
                "Try delete text with id {}");
    }
    /**
     * This is aspect of all text method find.
     * @param joinPointFindAll  point of enter
     * @return restore object
     */
    @Around("PointCuts.allFindMethodsText()")
    public Object aroundFindAllAdvice(
            final ProceedingJoinPoint joinPointFindAll) {
        return processMethod(joinPointFindAll,
                "findAllText",
                "Try find all article");
    }
    /**
     * This is aspect of text method save bulk text.
     * @param joinPoint  point of enter
     * @return restore object
     */
    @Around("PointCuts.saveMethodsText()")
    public Object aroundSaveBulkTextAdvice(
            final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        Object[] arguments = joinPoint.getArgs();
        if (methodSignature.getName().equals("saveBulkText")) {
            for (Object arg : arguments) {
                if (arg instanceof BulkTextRequestDTO bulkTextRequestDTO) {
                    bulkTextRequestDTO.getTexts().forEach(
                            each -> log.info("Try add text with information {}",
                                    each.getInformation()));
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
        if (methodSignature.getName().equals("saveBulkText")) {
            log.info("All text add");
        }
        return result;
    }
    /**
     * This is aspect of all text method save.
     * @param joinPointSave  point of enter
     * @return restore object
     */
    @Around("PointCuts.saveMethodsText()")
    public Object aroundSaveAdvice(
            final ProceedingJoinPoint joinPointSave) {
        return processMethod(joinPointSave,
                "saveText",
                "Try add text with information {}");
    }

    /**
     * This is aspect of all text method change.
     * @param joinPointChange  point of enter
     * @return restore object
     */
    @Around("PointCuts.changeMethodsText()")
    public Object aroundChangeAdvice(
            final ProceedingJoinPoint joinPointChange) {
        return processMethod(joinPointChange,
                "changeByText",
                "Try change by text {}");
    }
    /**
     * This is method of process.
     *
     * @param joinPointGeneral  point of enter
     * @param logMessage log message
     * @param methodName name method for
     * @return restore result
     */
    private Object processMethod(final ProceedingJoinPoint joinPointGeneral,
                                 final String methodName,
                                 final String logMessage) {
        MethodSignature methodSignature =
                (MethodSignature) joinPointGeneral.getSignature();
        Object result;
        try {
            if (methodSignature.getName().equals(methodName)) {
                Object[] arguments = joinPointGeneral.getArgs();
                for (Object arg : arguments) {
                    if (arg instanceof Text text) {
                        log.info(logMessage, text.getInformation());
                    }
                }
            }
            result = joinPointGeneral.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Method {} processed", methodName);
        return result;
    }
}
