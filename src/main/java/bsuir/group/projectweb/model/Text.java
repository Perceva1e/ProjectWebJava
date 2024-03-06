package bsuir.group.projectweb.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "text")
public class Text {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String information;
    @Column
    private String numberOfPhone;
    @Column
    private String email;

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
