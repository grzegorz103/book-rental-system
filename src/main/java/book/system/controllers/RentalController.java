package book.system.controllers;

import book.system.dto.RentalDTO;
import book.system.models.Book;
import book.system.models.Rental;
import book.system.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/rent")
@CrossOrigin
public class RentalController
{
        private final RentalService rentalService;

        @Autowired
        public RentalController ( RentalService rentalService )
        {
                this.rentalService = rentalService;
        }

        @GetMapping
        public List<RentalDTO> findAll ()
        {
                return rentalService.findAll();
        }

        @GetMapping ("/{id}")
        public RentalDTO rentBook ( @PathVariable ("id") Book book )
        {
                return rentalService.create( book );
        }

        @GetMapping ("/return/{id}")
        public RentalDTO returnBook ( @PathVariable ("id") Rental rental )
        {
                return rentalService.returnBook( rental );
        }
}
