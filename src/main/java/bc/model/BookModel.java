package bc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String bookName;
    private String shortDescription;
    @ManyToMany
    private Set<AuthorModel> authors;
}
