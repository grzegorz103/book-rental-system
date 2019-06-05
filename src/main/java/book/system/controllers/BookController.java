package book.system.controllers;

import book.system.models.Book;
import book.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/book")
public class BookController
{
        private final BookService bookService;

        @Autowired
        public BookController ( BookService bookService )
        {
                this.bookService = bookService;
        }

        @GetMapping
        @Secured ("ROLE_ADMIN")
        public List<Book> findAll ()
        {
                return bookService.findAll();
        }

        @PostMapping
        @Secured("ROLE_USER")
        public Book create ( @RequestBody Book book )
        {
                return bookService.create( book );
        }

        @DeleteMapping ("/{id}")
        @Secured("ROLE_USER")
        public boolean deleteById ( @PathVariable Long id )
        {
                return bookService.deleteById( id );
        }

        @DeleteMapping
        @Secured("ROLE_USER")
        public boolean delete ( @RequestBody Book book )
        {
                return bookService.delete( book );
        }
}
