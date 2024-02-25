package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
   void deleteByInformation(String information);
   Text findByInformation(String information);
}
