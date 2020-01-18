package book.system.mappers;

import book.system.dto.BookDTO;
import book.system.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO BookToDTO(Book book) {
        if (book == null) {
            return null;
        } else {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setBorrowed(book.isBorrowed());
            bookDTO.setPageNumber(book.getPageNumber());
            bookDTO.setPublishDate(book.getPublishDate());

            return bookDTO;
        }
    }

    public Book DTOtoBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        } else {
            Book book = new Book();
            book.setId(bookDTO.getId());
            book.setAuthor(bookDTO.getAuthor());
            book.setTitle(bookDTO.getTitle());
            book.setBorrowed(bookDTO.isBorrowed());
            book.setPageNumber(bookDTO.getPageNumber());
            book.setPublishDate(bookDTO.getPublishDate());

            return book;
        }
    }
}
