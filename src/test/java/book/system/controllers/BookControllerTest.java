package book.system.controllers;

import book.system.dto.BookDTO;
import book.system.dto.UserDTO;
import book.system.models.Book;
import book.system.services.BookService;
import book.system.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest
{
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private BookService bookService;

        private List<BookDTO> books;

        @Before
        public void setup ()
        {
                books = new ArrayList<>();
                books.add( BookDTO.builder().title( "testTitle" ).author( "testAuthor" ).build() );
                books.add( BookDTO.builder().title( "testTitle2" ).author( "testAuthor2" ).build() );
                books.add( BookDTO.builder().title( "testTitle3" ).author( "testAuthor3" ).build() );
        }

        @Test
        public void findAllBooksTest () throws Exception
        {
                when( bookService.findAll() ).thenReturn( books );
                this.mockMvc.perform( get( "/api/book" )
                        .with( httpBasic( "admin", "admin1" ) ) )
                        .andExpect( status().isOk() )
                        .andExpect( jsonPath( "$.length()" ).value( 3 ) );
        }

        @Test
        public void createBookTest () throws Exception
        {
                final BookDTO testBook = BookDTO.builder()
                        .title( "testTitle5" )
                        .author( "testAuthor5" )
                        .borrowed( false )
                        .pageNumber( 312 )
                        .id( 5L )
                        .publishDate( LocalDate.parse( LocalDate.now().format( DateTimeFormatter.ofPattern( "yyy-MM-dd" ) ) ) )
                        .build();
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule( new JavaTimeModule() );
                objectMapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
                when( bookService.create( testBook ) ).thenReturn( testBook );
                this.mockMvc.perform( post( "/api/book" )
                        .with( httpBasic( "admin", "admin1" ) )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( objectMapper.writeValueAsString( testBook ) ) )
                        .andExpect( status().isOk() )
                        .andExpect( jsonPath( "$.title" ).value( "testTitle5" ) );
        }

        @Test
        public void deleteBookByIdTest () throws Exception
        {
                this.mockMvc.perform( delete( "/api/book/{id}", "1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .with( httpBasic( "admin", "admin1" ) )
                        .accept( MediaType.APPLICATION_JSON ) )
                        .andExpect( status().isOk() );
        }

        @Test
        public void deleteBook () throws Exception
        {
                final Book testBook = Book.builder()
                        .title( "testTitle5" )
                        .author( "testAuthor5" )
                        .borrowed( false )
                        .pageNumber( 312 )
                        .id( 5L )
                        .publishDate( LocalDate.parse( LocalDate.now().format( DateTimeFormatter.ofPattern( "yyy-MM-dd" ) ) ) )
                        .build();
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule( new JavaTimeModule() );
                objectMapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );

                this.mockMvc.perform( delete( "/api/book/" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( objectMapper.writeValueAsString( testBook ) )
                        .with( httpBasic( "admin", "admin1" ) )
                        .accept( MediaType.APPLICATION_JSON ) )
                        .andExpect( status().isOk() );

        }
}
