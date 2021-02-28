package bc.controller;


import bc.dto.BookResponse;
import bc.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/book"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {"Book - Author"})
@RequiredArgsConstructor
public class AuthorController {

    private final BookService bookService;

//    @PostMapping(path = "/createBook")
//    @ApiOperation(value = "add Book")
//    public BookResponse addBook(@RequestBody CreateBookForm createBookForm) {
//        return bookService.addBook(createBookForm.getBookName(), createBookForm.getShortDescription(), createBookForm.getAuthors());
//    }


    @GetMapping(path = "/display-books")
    @ApiOperation(value = "display all Books")
    public Page<BookResponse> displayBooks(@RequestParam(value = "pageIndex") Integer pageIndex,
                                           @RequestParam(value = "pageSize") Integer pageSize) {
        return bookService.displayBooks(pageIndex, pageSize);
    }

}
