package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;


/** @noinspection ALL */
public interface TextRepositoryDAO extends JpaRepository<Text, Long> {
   Text findByInformation(String information);
   Text findTextByAuthors(Author author);
   Text findTextById(Long id);
   boolean existsByInformation(String information);
}
