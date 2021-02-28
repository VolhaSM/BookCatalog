package bc.service;

import bc.dto.AuthorResponse;
import bc.dto.BookResponse;
import bc.exceptions.AppException;
import bc.exceptions.HttpAppError;
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
import java.util.List;
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

    public AuthorResponse addAuthor(String authorName) {

        //Create new AuthorModel instance
        AuthorModel authorModel = new AuthorModel();

        // Set  the field
        authorModel.setName(authorName);

        //Save new instance to DataBase
        authorRepo.save(authorModel);


        return new AuthorResponse(authorModel);

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

    public Page<AuthorResponse> displayAuthors(Integer pageIndex, Integer pageSize) {

        // Create a Pageable type instance for using it in findAll method
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        // Find all entities from db, paginate and convert them to BookResponse type
        var authorResponses = authorRepo.findAll(pageable)
                .stream()
                .map(AuthorResponse::new)
                .collect(Collectors.toList());


        return new PageImpl<>(authorResponses, pageable, authorResponses.size());
    }

    public BookResponse updateBook(long id, String bookName, String shortDescription, Set<Long> authorsIds) {


        BookModel bookModel = bookRepo.findById(id).
                orElseThrow(() -> new AppException(HttpAppError.NOT_FOUND));

        //Ensures that all the parameters are not null and update the fields
        if (bookName != null) {
            bookModel.setBookName(bookName);
        }
        if (shortDescription != null) {
            bookModel.setShortDescription(shortDescription);
        }

        Set<AuthorModel> allAuthors = authorRepo.findAllByIdIn(authorsIds);
        bookModel.setAuthors(allAuthors);

        // Save new instance to DataBase
        bookRepo.save(bookModel);


        return new BookResponse(bookModel);
    }

    public AuthorResponse updateAuthor(long id, String authorName) {

        AuthorModel authorModel = authorRepo.findById(id).
                orElseThrow(() -> new AppException(HttpAppError.NOT_FOUND));

        //Ensures that all the parameters are not null and update the fields
        if (authorName != null) {
            authorModel.setName(authorName);
        }

        //Save new instance to DataBase
        authorRepo.save(authorModel);


        return new AuthorResponse(authorModel);
    }

    public List<BookResponse> searchBookByName(String parameter) {


        //Find all the entities from db, filter them, comparing with parameter
        return bookRepo.findAll().stream()
                .filter(b -> b.getBookName().toLowerCase().equals(parameter.toLowerCase()))
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    public List<AuthorResponse> searchAuthorByName(String parameter) {

        //Find all the entities from db, filter them, comparing with parameter
        return authorRepo.findAll().stream()
                .filter(a -> a.getName().toLowerCase().equals(parameter.toLowerCase()))
                .map(AuthorResponse::new)
                .collect(Collectors.toList());

    }

    public BookResponse deleteBook(long bookId) {

        //Ensures that id really exist in db and delete the entity
        BookModel bookModel = bookRepo.findById(bookId).orElseThrow(() -> new AppException(HttpAppError.NOT_FOUND));
        bookRepo.deleteById(bookModel.getId());

        return new BookResponse(bookModel);


    }


    public AuthorResponse deleteAuthor(long authorId) {

        //Ensures that id really exist in db
        AuthorModel author = authorRepo.findById(authorId).orElseThrow(() -> new AppException(HttpAppError.NOT_FOUND));
        // Delete from all connected entities and delete it itself
        bookRepo.deleteAllByAuthorsContains(author);
        authorRepo.delete(author);

        return new AuthorResponse(author);
    }
}
