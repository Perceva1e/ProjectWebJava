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
     * This is method logging all delete method in Text.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.deleteMethodsText()")
    public Object aroundDeleteAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        Long ids = null;
        if (methodSignature.getName().equals("deleteAuthorInText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long id) {
                    ids = id;
                    log.info("Try delete text with id {}", id);
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
        log.info("text with id {} delete", ids);
        return result;
    }

    /**
     * This is method logging all save method in Text.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.saveMethodsText()")
    public Object aroundSaveAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        Text texts = null;
        if (methodSignature.getName().equals("saveText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Text text) {
                    texts = text;
                    log.info("Try add text with information {}",
                            text.getInformation());
                }
            }
        }
        if (methodSignature.getName().equals("saveBulkText")) {
            Object[] arguments = joinPoint.getArgs();
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
        if (methodSignature.getName().equals("saveText")) {
            assert texts != null;
            log.info("Text with information {} add", texts.getInformation());
        }
        if (methodSignature.getName().equals("saveBulkText")) {
            log.info("All text add");
        }
        return result;
    }

    /**
     * This is method logging all change method in Text.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.changeMethodsText()")
    public Object aroundChangeAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        int countForArgs = 0;
        if (methodSignature.getName().equals("changeByText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String text) {
                    if (countForArgs == 1) {
                        text = (String) arg;
                        log.info("Try change by text {}", text);
                        break;
                    }
                    text = (String) arg;
                    countForArgs++;
                    log.info("Try change text {}", text);
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
        log.info("Method change text");
        return result;
    }

    /**
     * This is method logging all find method in Text.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.allFindMethodsText()")
    public Object aroundFindAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        String informations = null;
        /* TODO SMELL CODE */
        switch (methodSignature.getName()) {
            case "findAllText" -> log.info("Try get all information");
            case "findTextByInformation" -> {
                Object[] arguments = joinPoint.getArgs();
                for (Object arg : arguments) {
                    if (arg instanceof String information) {
                        informations = information;
                        log.info("Try find text by information {}",
                                information);
                    }
                }
            }
            case "findNumberPhoneAndEmail" -> {
                Object[] argument = joinPoint.getArgs();
                for (Object arg : argument) {
                    if (arg instanceof Text text) {
                        log.info("Try find Number Phone And Email "
                                + "with information {}", text.getInformation());
                    }
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
        switch (methodSignature.getName()) {
            case "findAllText" -> log.info("All text is get");
            case "findTextByInformation" -> log.info("Method find text "
                    + "by information {}", informations);
            case "findNumberPhoneAndEmail" -> {
                Object[] arguments = joinPoint.getArgs();
                for (Object arg : arguments) {
                    if (arg instanceof Text text) {
                        log.info("Method find Number Phone And Email "
                                + "with information {}", text.getInformation());
                    }
                }
            }
        }
        return result;
    }
}
