package book.system.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table (name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book
{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @Column (name = "author", length = 50)
        private String author;

        @Column (name = "title", length = 100)
        private String title;

        private LocalDate publishDate;

        @Column (name = "page_number")
        @Positive
        private Integer pageNumber;

        @Column (name = "borrowed")
        private boolean borrowed;
}
