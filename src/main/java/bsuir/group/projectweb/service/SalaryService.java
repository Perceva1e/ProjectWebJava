package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Salary;

public interface SalaryService {

    /**
     * This method demonstrates javadoc format.
     *
     * @param salary is an entity for save
     * @return restore salary after save
     */
    Salary saveSalary(Salary salary);

    /**
     * This method demonstrates javadoc format.
     *
     * @param id    is an id of salary
     * @param price is price for change
     * @return true is ok, false is error
     */
    Boolean changeSalaryById(Long id, Integer price);

    /**
     * This method demonstrates javadoc format.
     *
     * @param id is an id of text for delete
     * @return true is ok, false is error
     */
    boolean deleteSalary(Long id);
}
