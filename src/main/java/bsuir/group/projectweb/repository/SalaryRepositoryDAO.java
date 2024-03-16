package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SalaryRepositoryDAO extends JpaRepository<Salary, Long> {
    /**This method find salary by id.
     *@param id is an id for find
     *@return restore salary after find
     */
    Salary findSalariesById(Long id);
}
