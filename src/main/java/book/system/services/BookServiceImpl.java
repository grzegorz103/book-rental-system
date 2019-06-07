package book.system.services;

import book.system.dto.BookDTO;
import book.system.mappers.BookMapper;
import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service ("bookService")
public class BookServiceImpl implements BookService
{
        private final BookRepository bookRepository;

        private final BookMapper bookMapper;

        @Autowired
        public BookServiceImpl ( BookRepository bookRepository, BookMapper bookMapper )
        {
                this.bookRepository = bookRepository;
                this.bookMapper = bookMapper;
        }

        // returns dto with added id from db
        @Override
        public BookDTO create ( BookDTO bookDTO )
        {
                if ( bookDTO != null )
                {
                        Book mapped = bookMapper.DTOtoBook( bookDTO );
                        return bookMapper.BookToDTO( bookRepository.save( mapped ) );
                }
                return null;
        }

        @Override
        public List<BookDTO> findAll ()
        {
                return bookRepository.findAll()
                        .stream()
                        .map( bookMapper::BookToDTO )
                        .collect( Collectors.toList() );
        }

        @Override
        public boolean delete ( BookDTO bookDTO )
        {
                if ( bookDTO != null )
                {
                        if ( bookRepository.existsById( bookDTO.getId() ) )
                        {
                                Book mapped = bookMapper.DTOtoBook( bookDTO );
                                bookRepository.delete( mapped );
                                return true;
                        }
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
        public BookDTO update ( BookDTO book )
        {
                if ( book != null )
                {
                        Book mapped = bookMapper.DTOtoBook( book );
                        return bookMapper.BookToDTO( bookRepository.save( mapped ) );
                }
                return null;
        }
}
