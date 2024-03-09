package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepositoryDAO extends JpaRepository<Salary,Long> {
    Salary findSalariesById(Long id);

}
