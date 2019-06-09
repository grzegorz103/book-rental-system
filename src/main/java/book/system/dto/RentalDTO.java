package book.system.dto;

import book.system.models.Book;
import book.system.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO
{
        private Long id;
        private Book book;
        private LocalDate rentalDate;
        private LocalDate returnDate;
        private boolean returned;
        private User user;
        private Float penalty;
}
