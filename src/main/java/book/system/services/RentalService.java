package book.system.services;

import book.system.models.Book;
import book.system.models.Rental;

public interface RentalService
{
        Rental create ( Book book );

        String returnBook ( Rental rental );
}
