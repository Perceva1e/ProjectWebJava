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
    /**
     * This is method logging all delete method in Author.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.deleteMethodsAuthor()")
    public Object aroundDeleteAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        int countArg = 0;
        if (methodSignature.getName().equals("deleteAuthorConnection")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long id) {
                    if (countArg == 1) {
                        log.info("Try delete connection author "
                                + "in text with id {}", id);
                        break;
                    }
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

    /**
     * This is method logging all save method in Author.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.saveMethodsAuthor()")
    public Object aroundSaveAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        Author authors = null;
        if (methodSignature.getName().equals("savePerson")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Author author) {
                    authors = author;
                    log.info("Try add author with name {}",
                            author.getFirstName());
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
        assert authors != null;
        log.info("author with name {} add", authors.getFirstName());
        return result;
    }

    /**
     * This is method logging all find method in Author.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.findMethodsAuthor()")
    public Object aroundFindAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        String informations = null;
        if (methodSignature.getName().equals("findAuthorByParameters")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String information) {
                    information = (String) arg;
                    informations = information;
                    log.info("Try find all author with lastname {}",
                            information);
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
            log.info("Method find all author with lastname {}", informations);
        }
        return result;
    }

    /**
     * This is method logging all add method in Author.
     *
     * @param joinPoint this is point of enter
     * @return result is Object
     */
    @Around("PointCuts.addMethodsAuthor()")
    public Object aroundAddAdvice(final ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (
                MethodSignature) joinPoint.getSignature();
        if (methodSignature.getName().equals("addAuthorInText")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Author author) {
                    log.info("Try add author with name {}",
                            author.getFirstName());
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
