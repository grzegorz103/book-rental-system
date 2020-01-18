package book.system.services;

import book.system.dto.BookDTO;
import book.system.mappers.BookMapper;
import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    // returns dto with added id from db

    /**
     * Creates new book from given dto as parameter and stores in database.
     * Thid method uses mapper to convert entity to dto and in reverse
     *
     * @param bookDTO book that has to be created
     * @return entity that has been created, mapped to dto
     */
    @Override
    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO != null) {
            Book mapped = bookMapper.DTOtoBook(bookDTO);
            return bookMapper.BookToDTO(bookRepository.save(mapped));
        }
        return null;
    }

    /**
     * Returns all books stored in database
     *
     * @return list of books mapped to dto
     */
    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
            .stream()
            .map(bookMapper::BookToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Removes book stored in db by given dto
     * This method checks whether book stored book exists, and returns
     * true if book was removed successfully, otherwise false
     *
     * @param bookDTO book that has to be removed
     * @return result of removing
     */
    @Override
    public boolean delete(BookDTO bookDTO) {
        if (bookDTO != null) {
            if (bookRepository.existsById(bookDTO.getId())) {
                Book mapped = bookMapper.DTOtoBook(bookDTO);
                bookRepository.delete(mapped);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes book stored in db by given ID
     * This method checks whether book stored book exists, and returns
     * true if book was removed successfully, otherwise false
     *
     * @param id id of book that has to be removed
     * @return result of removing
     */
    @Override
    public boolean deleteById(Long id) {
        if (id != null && bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Updates existing book entity.
     *
     * @param book book with new values to be updated
     * @return updated book mapped to dto
     */
    @Override
    public BookDTO update(BookDTO book) {
        if (book != null) {
            Book mapped = bookMapper.DTOtoBook(book);
            return bookMapper.BookToDTO(bookRepository.save(mapped));
        }
        return null;
    }
}
