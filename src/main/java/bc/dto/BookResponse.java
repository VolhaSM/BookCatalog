package bc.dto;

import bc.model.AuthorModel;
import bc.model.BookModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data

public class BookResponse {

    private Long id;
    private String bookName;
    private Set<AuthorResponse> authors;

    public BookResponse(BookModel m) {
        this.id = m.getId();
        this.bookName = m.getBookName();

        if (m.getAuthors() != null) {
            this.authors = new HashSet<AuthorResponse>();
            for (AuthorModel a : m.getAuthors()) {
                this.authors.add(new AuthorResponse(a));

            }

        }
    }
}
