package book.system.controllers;

import book.system.models.Book;
import book.system.models.Rental;
import book.system.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/rent")
public class RentalController
{
        private final RentalService rentalService;

        @Autowired
        public RentalController ( RentalService rentalService )
        {
                this.rentalService = rentalService;
        }

        @PostMapping ("/{id}")
        public Rental create ( @PathVariable ("id") Book book )
        {
                return rentalService.create( book );
        }

        @PutMapping ("/{id}")
        public String returnBook ( @PathVariable ("id") Rental rental )
        {
                return rentalService.returnBook( rental );
        }
}
