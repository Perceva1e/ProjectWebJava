package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;


/** @noinspection ALL */
public interface TextRepositoryDAO extends JpaRepository<Text, Long> {
   Text findByInformation(String information);
   boolean existsByInformation(String information);
}
