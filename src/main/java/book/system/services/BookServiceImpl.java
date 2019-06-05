package book.system.services;

import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("bookService")
public class BookServiceImpl implements BookService
{
        private final BookRepository bookRepository;

        @Autowired
        public BookServiceImpl ( BookRepository bookRepository )
        {
                this.bookRepository = bookRepository;
        }

        @Override
        public Book create ( Book book )
        {
                if ( book != null )
                        return bookRepository.save( book );
                return null;
        }

        @Override
        public List<Book> findAll ()
        {
                return bookRepository.findAll();
        }

        @Override
        public boolean delete ( Book book )
        {
                if ( book != null && bookRepository.existsById( book.getId() ) )
                {
                        bookRepository.delete( book );
                        return true;
                }
                return false;
        }

        @Override
        public boolean deleteById ( Long id )
        {
                if ( id != null && bookRepository.existsById( id ) )
                {
                        bookRepository.deleteById( id );
                        return true;
                }
                return false;
        }

        @Override
        public Book update ( Book book )
        {
                return bookRepository.save( book );
        }
}
