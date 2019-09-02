package book.system.services;

import book.system.models.Book;
import book.system.models.Rental;
import book.system.models.User;
import book.system.repositories.RentalRepository;
import org.apache.tomcat.jni.Local;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

@Service ("rentalService")
public class RentalServiceImpl implements RentalService
{
        private final BookService bookService;

        private final RentalRepository rentalRepository;

        private final float FIRST_WEEK_PENALTY = 2.00f;

        private final float DAILY_PENALTY = 0.5f;

        @Autowired
        public RentalServiceImpl ( RentalRepository rentalRepository, BookService bookService )
        {
                this.rentalRepository = rentalRepository;
                this.bookService = bookService;
        }

        @Override
        @Transactional
        public Rental create ( Book book )
        {
                if ( hasPenalty( getCurrentUser() ) )
                        throw new RuntimeException( "You can't borrow a book because you have a penalty" );
                if ( book != null )
                {
                        if ( book.isBorrowed() )
                                throw new RuntimeException( "You can't rent a borrowed book" );

                        book.setBorrowed( true );
                        bookService.update( book );
                        LocalDate actualDate = LocalDate.now();
                        return rentalRepository.save(
                                Rental.builder()
                                        .book( book )
                                        .rentalDate( actualDate )
                                        .returnDate( actualDate.plusDays( 14 ) )
                                        .returned( false )
                                        .user( getCurrentUser() )
                                        .build()
                        );
                }
                return null;
        }

        private boolean hasPenalty ( User user )
        {
                return rentalRepository.findAllByUser( user )
                        .stream()
                        .filter( e -> !e.isReturned() )
                        .anyMatch( e -> LocalDate.now().isAfter( e.getReturnDate() ) );
        }

        private User getCurrentUser ()
        {
                return ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        @Override
        public String returnBook ( Rental rental )
        {
                if ( rental != null )
                {
                        Book rentBook = rental.getBook();
                        rentBook.setBorrowed( false );
                        bookService.update( rentBook );
                        rental.setReturned( true );
                        rentalRepository.save( rental );

                        LocalDate actualDate = LocalDate.now();
                        if ( actualDate.isAfter( rental.getReturnDate() ) )
                        {
                                long days = Duration.between( rental.getReturnDate().atStartOfDay(), actualDate.atStartOfDay() ).toDays();
                                if ( days > 0 )
                                {
                                        float penalty = days <= 7
                                                ? FIRST_WEEK_PENALTY
                                                : FIRST_WEEK_PENALTY + (days - 7) * DAILY_PENALTY;
                                        return "You exceeded the return date. Your penalty is " + penalty + "zł";
                                }
                        }
                        return "You haven't exceeded the return date.";
                } else
                {
                        throw new RuntimeException( "Incorrent rental!" );
                }
        }
}
