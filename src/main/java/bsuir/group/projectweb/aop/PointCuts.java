package bsuir.group.projectweb.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    /**
     * This is cuts of all find method in Text.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.TextServiceImpl.find*(..))")
    public void allFindMethodsText() {
    }

    /**
     * This is cuts of all save method in Text.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.TextServiceImpl.save*(..))")
    public void saveMethodsText() {
    }

    /**
     * This is cuts of all change method in Text.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.TextServiceImpl.change*(..))")
    public void changeMethodsText() {
    }

    /**
     * This is cuts of all delete method in Text.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service.impl."
            + "TextServiceImpl.delete*(..))")
    public void deleteMethodsText() {
    }

    /**
     * This is cuts of all save method in Author.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.AuthorServiceImpl.save*(..))")
    public void saveMethodsAuthor() {
    }

    /**
     * This is cuts of all delete method in Author.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.AuthorServiceImpl.delete*(..))")
    public void deleteMethodsAuthor() {
    }

    /**
     * This is cuts of all find method in Author.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.AuthorServiceImpl.find*(..))")
    public void findMethodsAuthor() {
    }

    /**
     * This is cuts of all add method in Author.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.AuthorServiceImpl.add*(..))")
    public void addMethodsAuthor() {
    }

    /**
     * This is cuts of all save method in Salary.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.SalaryServiceImpl.save*(..))")
    public void saveMethodsSalary() {
    }

    /**
     * This is cuts of all delete method in Salary.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.SalaryServiceImpl.delete*(..))")
    public void deleteMethodsSalary() {
    }

    /**
     * This is cuts of all change method in Salary.
     */
    @Pointcut("execution(* bsuir.group.projectweb.service."
            + "impl.SalaryServiceImpl.change*(..))")
    public void changeMethodsSalary() {
    }
}
