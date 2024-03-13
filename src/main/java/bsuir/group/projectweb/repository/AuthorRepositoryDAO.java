package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepositoryDAO extends JpaRepository<Author, Long> {
    /**This method demonstrates javadoc format.
     *@param lastName is a string for find
     *@param nameList is a list of string for find
     *@return restore list of author after find
     */
    @Query("select p from Author p where p.lastName=:lastName "
           + "and p.firstName in :nameList")
    List<Author> findAuthorByParameters(
             @Param("lastName") String lastName,
             @Param("nameList") List<String> nameList);
    /**This method demonstrates javadoc format.
     *@param id is an id of author for find
     *@return restore author after find
     */
    Author findAuthorById(Long id);
    /**This method demonstrates javadoc format.
     *@param salary is an entity for save
     *@return restore author after find
     */
    Author findAuthorBySalaries(Salary salary);

}
