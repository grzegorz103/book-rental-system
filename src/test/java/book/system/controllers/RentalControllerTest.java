package book.system.controllers;

import book.system.dto.RentalDTO;
import book.system.services.RentalServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RentalControllerTest
{
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private RentalServiceImpl rentalService;

        private List<RentalDTO> rentals;

        @Before
        public void setup ()
        {
                this.rentals = new ArrayList<>();
                rentals.add( new RentalDTO() );
                rentals.add( new RentalDTO() );
                rentals.add( new RentalDTO() );
        }

        @Test
        public void findAllRentals () throws Exception
        {
                when( rentalService.findAll() ).thenReturn( rentals );
                this.mockMvc.perform( get( "/api/rent" )
                        .with( user( "admin" ).password( "admin" ).roles( "ADMIN" ) ) )
                        .andExpect( status().isOk() )
                        .andExpect( jsonPath( "$.length()" ).value( 3 ) );
        }

        @Test
        public void rentBookTest () throws Exception
        {
                when( rentalService.create( any() ) ).thenReturn( any() );
                this.mockMvc.perform( get( "/api/rent/21" )
                        .with( user( "admin" ).password( "admin" ).roles( "ADMIN" ) ) )
                        .andExpect( status().isOk() );
        }

        @Test
        public void returnBookTest () throws Exception
        {
                when( rentalService.returnBook( any() ) ).thenReturn( any() );
                this.mockMvc.perform( get( "/api/rent/return/21" )
                        .with( user( "admin" ).password( "admin" ).roles( "ADMIN" ) ) )
                        .andExpect( status().isOk() );
        }
}
