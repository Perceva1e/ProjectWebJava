package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "text")
public class Text {
    /**This method demonstrates javadoc format.
     *@param id is an id of text
     */
    @Id
    @GeneratedValue
    private Long id;
    /**This method demonstrates javadoc format.
     *@param information is an information of text
     */
    @Column
    private String information;
    /**This method demonstrates javadoc format.
     *@param numberOfPhone is a confidence data
     */
    @Column
    private String numberOfPhone;
    /**This method demonstrates javadoc format.
     *@param email is a confidence data
     */
    @Column
    private String email;
    /**This method demonstrates javadoc format.
     *@param authors is a set of author for link
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "text_author",
            joinColumns = {
                    @JoinColumn(name = "text_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
            }
    )
    private Set<Author> authors;
}
