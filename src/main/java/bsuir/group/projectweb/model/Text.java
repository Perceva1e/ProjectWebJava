package bsuir.group.projectweb.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "text")
public class Text {
    @Id
    @GeneratedValue
    private Long id;
    private String information;
    private String numberOfPhone;
    private String email;
}
