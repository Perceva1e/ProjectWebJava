package bsuir.group.projectweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import lombok.Data;


@Data
@Entity
@Table(name = "author")
public class Author {
    /**
     * This id of object.
     *
     * @param id is an id of entity
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * This firstname.
     *
     * @param firstName is a name of author
     */
    @Column
    private String firstName;
    /**
     * This lastname.
     *
     * @param lastName is a lastname of author
     */
    @Column
    private String lastName;
    /**
     * This may to one with salary.
     *
     * @param salaries is an entity for link
     */
    @ManyToOne
    @JoinColumn(name = "author_salary")
    private Salary salaries;
}
