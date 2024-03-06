package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Integer salary;
}
