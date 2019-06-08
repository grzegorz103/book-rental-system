package book.system.services;

import book.system.models.Book;
import book.system.models.Rental;

import java.util.List;

public interface RentalService
{
        List<Rental> findAll();

        Rental create ( Book book );

        Rental returnBook ( Rental rental );
}
