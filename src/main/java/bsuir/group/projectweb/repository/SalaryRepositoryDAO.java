package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SalaryRepositoryDAO extends JpaRepository<Salary, Long> {
    Salary findSalariesById(Long id);

}
