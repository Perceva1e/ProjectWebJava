package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepositoryDAO extends JpaRepository<Text, Long> {
   Text findByInformation(String information);
}
