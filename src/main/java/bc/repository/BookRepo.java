package bc.repository;

import bc.model.AuthorModel;
import bc.model.BookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository <BookModel, Long> {

    Page<BookModel> findAll(Pageable pageable);

    void deleteAllByAuthorsContains(AuthorModel author);
}
