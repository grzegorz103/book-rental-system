package book.system.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "rents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental
{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn (name = "book_id", nullable = false)
        private Book book;

        @Column (name = "rental_date")
        private LocalDate rentalDate;

        @Column (name = "return_date")
        private LocalDate returnDate;

        @Column (name = "returned")
        private boolean returned;

        @ManyToOne
        @JoinColumn (name = "user_id", nullable = false)
        private User user;

        @Column (name = "penalty")
        private Float penalty;
}
