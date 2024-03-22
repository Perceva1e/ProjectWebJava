package bsuir.group.projectweb.aop;

import bsuir.group.projectweb.model.Author;
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
public class AuthorAspect {
    @Around("PointCuts.deleteMethodsAuthor()")
    public Object aroundDeleteAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        int countArg = 0;
        if (methodSignature.getName().equals("deleteAuthorConnection")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long id) {
                    if (countArg == 1) {
                        log.info("Try delete connection author in text with id {}", id);
                        break;
                    }
                    id = (Long) arg;
                    log.info("Try delete connection author with id {}", id);
                    countArg++;
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
        log.info("author connection was delete");
        return result;
    }

    @Around("PointCuts.saveMethodsAuthor()")
    public Object aroundSaveAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Author author = null;
        if (methodSignature.getName().equals("savePerson")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Author) {
                    author = (Author) arg;
                    log.info("Try add author with name {}", author.getFirstName());
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
        assert author != null;
        log.info("author with name {} add", author.getFirstName());
        return result;
    }

    @Around("PointCuts.findMethodsAuthor()")
    public Object aroundFindAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String information = null;
        if (methodSignature.getName().equals("findAuthorByParameters")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    information = (String) arg;
                    log.info("Try find all author with lastname {}", information);
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
        if (methodSignature.getName().equals("findAuthorByParameters")) {
            log.info("Method find all author with lastname {}", information);
        }
        return result;
    }

    @Around("PointCuts.addMethodsAuthor()")
    public Object aroundAddAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Author author;
        if (methodSignature.getName().equals("addAuthorInText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Author) {
                    author = (Author) arg;
                    log.info("Try add author with name {}", author.getFirstName());
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
        log.info("author was add in text");
        return result;
    }
}
