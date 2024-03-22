package bsuir.group.projectweb.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.TextServiceImpl.find*(..))")
    public void allFindMethodsText() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.TextServiceImpl.save*(..))")
    public void saveMethodsText() {
    }

    @Pointcut("execution(* bsuir.group.projectweb.service.impl.TextServiceImpl.change*(..))")
    public void changeMethodsText() {
    }

    @Pointcut("execution(* bsuir.group.projectweb.service.impl.TextServiceImpl.delete*(..))")
    public void deleteMethodsText() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.AuthorServiceImpl.save*(..))")
    public void saveMethodsAuthor() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.AuthorServiceImpl.delete*(..))")
    public void deleteMethodsAuthor() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.AuthorServiceImpl.find*(..))")
    public void findMethodsAuthor() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.AuthorServiceImpl.add*(..))")
    public void addMethodsAuthor() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.SalaryServiceImpl.save*(..))")
    public void saveMethodsSalary() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.SalaryServiceImpl.delete*(..))")
    public void deleteMethodsSalary() {
    }
    @Pointcut("execution(* bsuir.group.projectweb.service.impl.SalaryServiceImpl.change*(..))")
    public void changeMethodsSalary() {
    }
}
