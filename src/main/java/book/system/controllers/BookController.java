package book.system.controllers;

import book.system.dto.BookDTO;
import book.system.models.Book;
import book.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Finds all books mapped to DTO.
     *
     * @return list of all books
     */
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    /**
     * Allows to create new books
     *
     * @param bookDTO book that has to be created
     * @return created book
     */
    @PostMapping
    @Secured("ROLE_ADMIN")
    public BookDTO create(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    /**
     * Deletes book by given id
     *
     * @param id book's id that has to be deleted
     * @return true is book has been removed, otherwise false
     */
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public boolean deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }

    /**
     * Deletes book by given object
     *
     * @param book
     * @return
     */
    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public boolean delete(@RequestBody BookDTO book) {
        return bookService.delete(book);
    }
}
