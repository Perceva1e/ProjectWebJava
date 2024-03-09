package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/** @noinspection ALL */
public interface AuthorRepositoryDAO extends JpaRepository<Author, Long> {
    @Query("select p from Author p where p.lastName=:lastName and p.firstName in :nameList")
    List<Author> findAuthorByParameters(@Param("lastName") String lastName,
                                        @Param("nameList") List<String> nameList);
    Author findAuthorById(Long id);
    Author findAuthorBySalaries(Salary salary);

}
