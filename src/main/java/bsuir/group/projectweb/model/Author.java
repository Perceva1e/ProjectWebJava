package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "author_salary")
    private Salary salaries;
}
