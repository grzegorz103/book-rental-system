package book.system.services;

import book.system.models.Book;

import java.util.List;

public interface BookService
{
        Book create ( Book book );

        List<Book> findAll ();

        boolean delete ( Book book );

        boolean deleteById ( Long id );
}
