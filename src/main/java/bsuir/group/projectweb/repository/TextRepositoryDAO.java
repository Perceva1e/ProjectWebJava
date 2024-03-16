package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TextRepositoryDAO extends JpaRepository<Text, Long> {
   /**This method find by information.
    *@param information is a string for find
    *@return restore text after find
    */
   Text findByInformation(String information);
   /**This method find Text by Authors.
    *@param author is an entity for find
    *@return restore text after find
    */
   Text findTextByAuthors(Author author);
   /**This method demonstrates javadoc format.
    *@param id is an id of text for find
    *@return restore text after find
    */
   Text findTextById(Long id);
   /**This method exists by Information.
    *@param information is a string for find
    *@return restore text after find
    */
   boolean existsByInformation(String information);
   /**This method find First by Information.
    *@param information is a string for find
    *@return restore first text after find
    */
   Text findFirstByInformation(String information);
}
