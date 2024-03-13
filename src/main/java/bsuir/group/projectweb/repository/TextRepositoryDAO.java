package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TextRepositoryDAO extends JpaRepository<Text, Long> {
   /**This method demonstrates javadoc format.
    *@param information is a string for find
    *@return restore text after find
    */
   Text findByInformation(String information);
   /**This method demonstrates javadoc format.
    *@param author is an entity for find
    *@return restore text after find
    */
   Text findTextByAuthors(Author author);
   /**This method demonstrates javadoc format.
    *@param id is an id of text for find
    *@return restore text after find
    */
   Text findTextById(Long id);
   /**This method demonstrates javadoc format.
    *@param information is a string for find
    *@return restore text after find
    */
   boolean existsByInformation(String information);
}
