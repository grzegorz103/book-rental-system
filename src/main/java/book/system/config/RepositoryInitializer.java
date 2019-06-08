package book.system.config;

import book.system.models.Book;
import book.system.models.User;
import book.system.models.UserRole;
import book.system.repositories.BookRepository;
import book.system.repositories.UserRepository;
import book.system.repositories.UserRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class RepositoryInitializer
{
        private final BookRepository bookRepository;

        private final UserRepository userRepository;

        private final UserRoleRepository userRoleRepository;

        private final BCryptPasswordEncoder encoder;

        @Autowired
        public RepositoryInitializer ( BookRepository bookRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder encoder )
        {
                this.bookRepository = bookRepository;
                this.userRepository = userRepository;
                this.userRoleRepository = userRoleRepository;
                this.encoder = encoder;
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
                                                .borrowed( false )
                                                .publishDate( LocalDate.of( 1999, 5, 2 ) )
                                                .build()
                                );

                                bookRepository.save(
                                        Book.builder()
                                                .author( "Adam Mickiewicz" )
                                                .title( "Pan Tadeusz" )
                                                .pageNumber( 320 )
                                                .borrowed( false )
                                                .publishDate( LocalDate.of( 1995, 4, 8 ) )
                                                .build()
                                );

                                bookRepository.save(
                                        Book.builder()
                                                .author( "Adam Mickiewicz" )
                                                .title( "Sonety krymskie" )
                                                .pageNumber( 433 )
                                                .borrowed( false )
                                                .publishDate( LocalDate.of( 1994, 1, 10 ) )
                                                .build()
                                );
                        }

                        if ( userRoleRepository.findAll().isEmpty() )
                        {
                                userRoleRepository.save( new UserRole( 1L, UserRole.UserType.ROLE_USER ) );
                                userRoleRepository.save( new UserRole( 2L, UserRole.UserType.ROLE_ADMIN ) );
                        }

                        if ( userRepository.findAll().isEmpty() )
                        {
                                userRepository.save(
                                        User.builder()
                                                .username( "admin" )
                                                .password( encoder.encode( "admin1" ) )
                                                .enabled( true )
                                                .locked( false )
                                                .credentials( false )
                                                .userRoles( new HashSet<>( Arrays.asList( userRoleRepository.findByUserType( UserRole.UserType.ROLE_ADMIN ), userRoleRepository.findByUserType( UserRole.UserType.ROLE_USER ) ) ) )
                                                .build()
                                );

                                userRepository.save(
                                        User.builder()
                                                .username( "user" )
                                                .password( encoder.encode( "user" ) )
                                                .enabled( true )
                                                .credentials( false )
                                                .locked( false )
                                                .userRoles( new HashSet<>( Collections.singleton( userRoleRepository.findByUserType( UserRole.UserType.ROLE_USER ) ) ) )
                                                .build()
                                );
                        }
                };
        }
}
