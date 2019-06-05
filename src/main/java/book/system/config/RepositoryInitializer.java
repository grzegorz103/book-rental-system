package book.system.config;

import book.system.models.Book;
import book.system.repositories.BookRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class RepositoryInitializer
{
        private final BookRepository bookRepository;

        @Autowired
        public RepositoryInitializer ( BookRepository bookRepository )
        {
                this.bookRepository = bookRepository;
        }

        @Bean
        public InitializingBean initializingBean ()
        {
                return () -> {
                        if ( bookRepository.findAll().isEmpty() )
                        {
                                bookRepository.save(
                                        Book.builder()
                                                .author( "Henryk Sienkiewicz" )
                                                .title( "Potop" )
                                                .pageNumber( 120 )
                                                .publishDate( LocalDate.of( 1999, 5, 2 ) )
                                                .build()
                                );

                                bookRepository.save(
                                        Book.builder()
                                                .author( "Adam Mickiewicz" )
                                                .title( "Pan Tadeusz" )
                                                .pageNumber( 320 )
                                                .publishDate( LocalDate.of( 1995, 4, 8 ) )
                                                .build()
                                );

                                bookRepository.save(
                                        Book.builder()
                                                .author( "Adam Mickiewicz" )
                                                .title( "Sonety krymskie" )
                                                .pageNumber( 433 )
                                                .publishDate( LocalDate.of( 1994, 1, 10 ) )
                                                .build()
                                );
                        }
                };
        }
}
