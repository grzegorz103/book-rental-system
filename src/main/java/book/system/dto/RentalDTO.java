package book.system.dto;

import book.system.models.Book;
import book.system.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Long id;

    @NotNull
    private Book book;

    @NotNull
    private LocalDate rentalDate;

    @NotNull
    private LocalDate returnDate;
    private boolean returned;

    @NotNull
    private User user;

    @PositiveOrZero
    private Float penalty;
}
