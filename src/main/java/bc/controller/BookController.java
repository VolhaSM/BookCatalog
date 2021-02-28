package bc.controller;

import bc.dto.AuthorResponse;
import bc.dto.BookResponse;
import bc.form.CreateAuthorForm;
import bc.form.CreateBookForm;
import bc.form.UpdateAuthorForm;
import bc.form.UpdateBookForm;
import bc.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/book"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {"Book - Author"})
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "/createBook")
    @ApiOperation(value = "add Book")
    public BookResponse addBook(@RequestBody CreateBookForm createBookForm) {
        return bookService.addBook(createBookForm.getBookName(), createBookForm.getShortDescription(), createBookForm.getAuthors());
    }

    @PostMapping(path = "/createAuthor")
    @ApiOperation(value = "add Author")
    public AuthorResponse addAuthor(
            @RequestBody CreateAuthorForm createAuthorForm) {

        return bookService.addAuthor(createAuthorForm.getName());
    }

    @PutMapping(path = "update-book")
    @ApiOperation(value = "update Book")
    public BookResponse updateBook(@RequestBody UpdateBookForm updateBookForm) {
        return bookService.updateBook(updateBookForm.getId(),
                updateBookForm.getBookName(),
                updateBookForm.getShortDescription(),
                updateBookForm.getAuthors());
    }

    @PutMapping(path = "update-author")
    @ApiOperation(value = "update Author")
    public AuthorResponse updateAuthor(@RequestBody UpdateAuthorForm updateAuthorForm) {
        return bookService.updateAuthor(updateAuthorForm.getId(), updateAuthorForm.getName());
    }

    @GetMapping(path = "/display-books")
    @ApiOperation(value = "display all Books")
    public Page<BookResponse> displayBooks(@RequestParam(value = "pageIndex") Integer pageIndex,
                                           @RequestParam(value = "pageSize") Integer pageSize) {
        return bookService.displayBooks(pageIndex, pageSize);
    }

    @GetMapping(path = "/display-authors")
    @ApiOperation(value = "display All Authors")
    public Page<AuthorResponse> displayAuthors(@RequestParam(value = "pageIndex") Integer pageIndex,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        return bookService.displayAuthors(pageIndex, pageSize);
    }

    @GetMapping(path = "find-book")
    @ApiOperation(value = "find Book ")
    public List<BookResponse> searchBookByName(@RequestParam(value = "parameter") String parameter) {
        return bookService.searchBookByName(parameter);
    }

    @GetMapping(path = "find-author")
    @ApiOperation(value = "find Author")
    public List<AuthorResponse> searchAuthorByName(@RequestParam(value = "parameter") String parameter) {

        return bookService.searchAuthorByName(parameter);
    }

    @DeleteMapping(path = "delete-book")
    @ApiOperation(value = "delete Book")

    public BookResponse deleteBook(@RequestParam(value = "id") long bookId) {

        return bookService.deleteBook(bookId);
    }

    @DeleteMapping(path = "delete-author")
    @ApiOperation(value = "delete Author")
    public AuthorResponse deleteAuthor(@RequestParam(value = "id") long authorId) {
        bookService.deleteAuthor(authorId);

        return bookService.deleteAuthor(authorId);
    }

}
