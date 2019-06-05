package book.system.services;

import book.system.models.Book;
import book.system.models.Rental;
import book.system.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        private final float DAY_PENALTY = 0.5f;

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
                if ( book != null )
                {
                        if ( book.isBorrowed() )
                                throw new RuntimeException( "You can't rent a borrowed book" );

                        rentalRepository.save(
                                Rental.builder()
                                        .book( book )
                                        .rentalDate( LocalDate.now() )
                                        .returnDate( null )
                                        .build()
                        );
                        book.setBorrowed( true );
                        bookService.update( book );
                }
                return null;
        }

        @Override
        public String returnBook ( Rental rental )
        {
                if ( rental != null && rental.getReturnDate() == null )
                {
                        Book rentBook = rental.getBook();
                        rentBook.setBorrowed( false );
                        bookService.update( rentBook );
                        float penalty = 0;
                        rental.setReturnDate( LocalDate.now() );
                        long days = Duration.between( rental.getReturnDate(), rental.getRentalDate() ).toDays();
                        if ( days > 0 )
                                penalty = days <= 7
                                        ? FIRST_WEEK_PENALTY
                                        : FIRST_WEEK_PENALTY + (days - 7) * DAY_PENALTY;
                        return "You exceeded the return date. Your penalty is " + penalty;
                } else
                {
                        throw new RuntimeException( "Incorrent rental!" );
                }
        }
}
