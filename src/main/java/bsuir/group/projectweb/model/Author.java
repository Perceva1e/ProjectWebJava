package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "author")
public class Author {
    /**This method demonstrates javadoc format.
     *@param id is an id of entity
     */
    @Id
    @GeneratedValue
    private Long id;
    /**This method demonstrates javadoc format.
     *@param firstName is a name of author
     */
    @Column
    private String firstName;
    /**This method demonstrates javadoc format.
     *@param lastName is a lastname of author
     */
    @Column
    private String lastName;
    /**This method demonstrates javadoc format.
     *@param salaries is an entity for link
     */
    @ManyToOne
    @JoinColumn(name = "author_salary")
    private Salary salaries;
}
