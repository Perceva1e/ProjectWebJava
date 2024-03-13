package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "salary")
public class Salary {
    /**This method demonstrates javadoc format.
     *@param id is an id of entity
     */
    @Id
    @GeneratedValue
    private Long id;
    /**This method demonstrates javadoc format.
     *@param price is a price of author
     */
    @Column
    private Integer price;
}
