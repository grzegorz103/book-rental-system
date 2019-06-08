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

        @NotBlank
        private String author;

        @NotBlank
        private String title;

        @NotNull
        private LocalDate publishDate;

        @Positive
        @NotNull
        private Integer pageNumber;

        private boolean borrowed;
}
