package bsuir.group.projectweb.model;


import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
@Data
@Entity
@Table(name = "salary")
public class Salary {
    /**This id of object.
     *@param id is an id of entity
     */
    @Id
    @GeneratedValue
    private Long id;
    /**This price.
     *@param price is a price of author
     */
    @Column
    private Integer price;
}
