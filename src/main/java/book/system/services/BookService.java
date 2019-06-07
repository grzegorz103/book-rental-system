package book.system.services;

import book.system.dto.BookDTO;
import book.system.models.Book;

import java.util.List;

public interface BookService
{
        BookDTO create ( BookDTO bookDTO );

        List<BookDTO> findAll ();

        boolean delete ( BookDTO bookDTO );

        boolean deleteById ( Long id );

        BookDTO update ( BookDTO book );
}
