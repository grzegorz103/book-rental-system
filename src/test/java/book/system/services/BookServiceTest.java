package book.system.services;

import book.system.dto.BookDTO;
import book.system.mappers.BookMapper;
import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Spy
    private BookMapper bookMapper;

    private List<BookDTO> books;

    @Before
    public void setup() {
        this.books = new ArrayList<>();
        books.add(
            BookDTO.builder()
                .id(1L)
                .author("TestAuthor")
                .title("TestTitle")
                .pageNumber(150)
                .build()
        );
        books.add(
            BookDTO.builder()
                .id(2L)
                .author("TestAuthor2")
                .title("TestTitle2")
                .pageNumber(120)
                .build()
        );
    }

    @Test
    public void findAllBooksTest() {
        bookService.findAll();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void createBookTest() {
        BookDTO bookDTO = BookDTO.builder().author("testAuthor").title("testTitle").build();
        bookService.create(bookDTO);

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void deleteBookTest() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        assertThat(bookService.delete(books.get(0))).isEqualTo(true);
        assertFalse(bookService.delete(null));
    }

    @Test
    public void deleteBookByIdTest() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        assertThat(bookService.delete(books.get(0))).isEqualTo(true);
        assertFalse(bookService.deleteById(null));
    }
}
