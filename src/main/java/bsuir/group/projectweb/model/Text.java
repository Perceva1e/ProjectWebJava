package bsuir.group.projectweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "text")
public class Text {
    /**This id of object.
     *@param id is an id of text
     */
    @Id
    @GeneratedValue
    private Long id;
    /**This information.
     *@param information is an information of text
     */
    @Column
    private String information;
    /**This number of phone.
     *@param numberOfPhone is a confidence data
     */
    @Column
    private String numberOfPhone;
    /**This email.
     *@param email is a confidence data
     */
    @Column
    private String email;
    /**This many to many with authors.
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
