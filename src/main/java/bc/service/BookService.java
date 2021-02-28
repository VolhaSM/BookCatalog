package bc.service;

import bc.dto.BookResponse;
import bc.model.AuthorModel;
import bc.model.BookModel;
import bc.repository.AuthorRepo;
import bc.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookResponse addBook(String bookName, String shortDescription, Set<Long> authors) {

        // Create new BookModel instance
        BookModel bookModel = new BookModel();

        //Set all the fields
        bookModel.setBookName(bookName);
        bookModel.setShortDescription(shortDescription);
        Set<AuthorModel> allAuthors = authorRepo.findAllByIdIn(authors);
        bookModel.setAuthors(allAuthors);

        //Save new instance to DataBase
        bookRepo.save(bookModel);

        return new BookResponse(bookModel);

    }

    public Page<BookResponse> displayBooks(Integer pageIndex, Integer pageSize) {

        // Create a Pageable type instance for using it in findAll method
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        // Find all entities from db, paginate and convert them to BookResponse type
        var bookResponses = bookRepo.findAll(pageable).stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());

        return new PageImpl<>(bookResponses, pageable, bookResponses.size());
    }
}
