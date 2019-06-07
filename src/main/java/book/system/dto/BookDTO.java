package book.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO
{
        private Long id;

        @Column (name = "author", length = 50)
        @NotBlank
        private String author;

        @Column (name = "title", length = 100)
        @NotBlank
        private String title;

        @NotNull
        private LocalDate publishDate;

        @Column (name = "page_number")
        @Positive
        @NotNull
        private Integer pageNumber;

        private boolean borrowed;
}
