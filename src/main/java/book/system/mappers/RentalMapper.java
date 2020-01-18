package book.system.mappers;

import book.system.dto.RentalDTO;
import book.system.models.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    public Rental DTOtoRental(RentalDTO rentalDTO) {
        if (rentalDTO == null) {
            return null;
        } else {
            Rental rental = new Rental();
            rental.setPenalty(rentalDTO.getPenalty());
            rental.setId(rentalDTO.getId());
            rental.setReturned(rentalDTO.isReturned());
            rental.setBook(rentalDTO.getBook());
            rental.setReturnDate(rentalDTO.getReturnDate());
            rental.setUser(rentalDTO.getUser());
            rental.setRentalDate(rentalDTO.getRentalDate());
            return rental;
        }
    }

    public RentalDTO rentalToDTO(Rental rental) {

        if (rental == null) {
            return null;
        } else {
            RentalDTO rentalDTO = new RentalDTO();
            rentalDTO.setPenalty(rental.getPenalty());
            rentalDTO.setId(rental.getId());
            rentalDTO.setReturned(rental.isReturned());
            rentalDTO.setBook(rental.getBook());
            rentalDTO.setReturnDate(rental.getReturnDate());
            rentalDTO.setUser(rental.getUser());
            rentalDTO.setRentalDate(rental.getRentalDate());
            return rentalDTO;
        }
    }
}
