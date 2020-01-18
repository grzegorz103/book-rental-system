package book.system.services;

import book.system.dto.RentalDTO;
import book.system.models.Book;
import book.system.models.Rental;

import java.util.List;

public interface RentalService {
    List<RentalDTO> findAll();

    RentalDTO create(Book book);

    RentalDTO returnBook(Rental rental);
}
