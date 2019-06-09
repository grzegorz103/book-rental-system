package book.system.services;

import book.system.dto.BookDTO;
import book.system.dto.RentalDTO;
import book.system.mappers.BookMapper;
import book.system.mappers.RentalMapper;
import book.system.models.Book;
import book.system.models.Rental;
import book.system.repositories.RentalRepository;
import com.sun.org.apache.regexp.internal.RE;
import jdk.Exported;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith (MockitoJUnitRunner.class)
public class RentalServiceTest
{
        @Spy
        private RentalRepository rentalRepository;

        @Mock
        private BookService bookService;

        @InjectMocks
        private RentalServiceImpl rentalService;

        @Spy
        private RentalMapper rentalMapper;

        @Spy
        private BookMapper bookMapper;

        @Test
        public void findAllRentsTest ()
        {
                List<Rental> rentals = new ArrayList<>();
                rentals.add( mock( Rental.class ) );
                rentals.add( mock( Rental.class ) );
                when( rentalRepository.findAll() ).thenReturn( rentals );
                rentalService.findAll();
                verify( rentalRepository ).findAll();
                verify( rentalMapper, atLeastOnce() ).rentalToDTO( any( Rental.class ) );
        }

        @Test (expected = RuntimeException.class)
        public void createRentalWithBorrowedBookTest ()
        {
                Book book = Book.builder().borrowed( true ).build();
                rentalService.create( book );
        }

        @Test
        public void createRental ()
        {
                Authentication authentication = Mockito.mock( Authentication.class );
                SecurityContext securityContext = Mockito.mock( SecurityContext.class );
                when( securityContext.getAuthentication() ).thenReturn( authentication );
                SecurityContextHolder.setContext( securityContext );
                Book book = Book.builder().borrowed( false ).author( "testAuthor" ).title( "testTitle" ).build();
                when( bookService.update( any( BookDTO.class ) ) ).thenReturn( null );
                rentalService.create( book );

                verify( bookService ).update( any( BookDTO.class ) );
                verify( rentalRepository ).save( any( Rental.class ) );
        }

        @Test (expected = RuntimeException.class)
        public void returnIncorrectBookTest ()
        {
                rentalService.returnBook( null );
        }

        @Test
        public void returnBookTest ()
        {
                Rental rental = mock( Rental.class );
                rentalService.returnBook( rental );
                verify( rentalRepository ).save( any( Rental.class ) );
        }
}
