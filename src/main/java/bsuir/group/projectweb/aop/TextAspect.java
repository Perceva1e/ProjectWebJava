package bsuir.group.projectweb.aop;

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
    @Around("PointCuts.deleteMethodsText()")
    public Object aroundDeleteAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
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

    @Around("PointCuts.saveMethodsText()")
    public Object aroundSaveAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Text texts = null;
        if (methodSignature.getName().equals("saveText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Text text) {
                    texts = text;
                    log.info("Try add text with information {}", text.getInformation());
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
        assert texts != null;
        log.info("text with information {} add", texts.getInformation());
        return result;
    }

    @Around("PointCuts.changeMethodsText()")
    public Object aroundChangeAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String text;
        int countForArgs = 0;
        if (methodSignature.getName().equals("changeByText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
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

    @Around("PointCuts.allFindMethodsText()")
    public Object aroundFindAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String informations = null;
        if (methodSignature.getName().equals("findAllText")) {
            log.info("Try get all information");
        }
        if (methodSignature.getName().equals("findTextByInformation")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String information) {
                    informations = information;
                    log.info("Try find text by information {}", information);
                }
            }

        }
        if (methodSignature.getName().equals("findNumberPhoneAndEmail")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Text text) {
                    log.info("Try find Number Phone And Email with information {}", text.getInformation());
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
        if (methodSignature.getName().equals("findAllText")) {
            log.info("All text is get");
        }
        if (methodSignature.getName().equals("findTextByInformation")) {
            log.info("Method find text by information {}", informations);
        }
        if (methodSignature.getName().equals("findNumberPhoneAndEmail")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Text text) {
                    log.info("Method find Number Phone And Email with information {}", text.getInformation());
                }
            }
        }
        return result;
    }
}
