package book.system.services;

import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class BookServiceTest
{
        @Mock
        private BookRepository bookRepository;

        @InjectMocks
        private BookServiceImpl bookService;

        private List<Book> books;

        @Before
        public void setup ()
        {
                this.books = new ArrayList<>();
                books.add(
                        Book.builder()
                                .id( 1L )
                                .author( "TestAuthor" )
                                .title( "TestTitle" )
                                .ISBN( "978-3-16-148410-0" )
                                .build()
                );
                books.add(
                        Book.builder()
                                .id( 2L )
                                .author( "TestAuthor2" )
                                .title( "TestTitle2" )
                                .ISBN( "978-3-16-143954-0" )
                                .build()
                );
                when( bookRepository.findAll() ).thenReturn( books );
        }

        @Test
        public void testFindAllBooks ()
        {
                assertThat( bookService.findAll() ).isEqualTo( this.books );
        }

        @Test
        public void testCreateBook ()
        {
                Book test = Book.builder()
                        .id( 2L )
                        .author( "TestAuthor3" )
                        .title( "TestTitle3" )
                        .ISBN( "978-3-16-121954-0" )
                        .build();
                when( bookRepository.save( test ) ).thenReturn( test );
                assertThat( bookService.create( test ) ).isEqualTo( test );
        }

        @Test
        public void testDeleteBook ()
        {
                when( bookRepository.existsById( 1L ) ).thenReturn( true );
                assertThat( bookService.delete( books.get( 0 ) ) ).isEqualTo( true );
                assertFalse( bookService.delete( null ) );
        }

        @Test
        public void testDeleteBookById ()
        {
                when( bookRepository.existsById( 1L ) ).thenReturn( true );
                assertThat( bookService.delete( books.get( 0 ) ) ).isEqualTo( true );
                assertFalse( bookService.deleteById( null ) );
        }
}
