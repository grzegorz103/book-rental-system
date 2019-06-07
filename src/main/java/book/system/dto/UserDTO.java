package book.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
        private Long id;

        @Length (min = 4, max = 50)
        @NotEmpty
        private String username;

        @Length (min = 6)
        @NotEmpty
        private String password;

        @Length (min = 6)
        @NotEmpty
        private String passwordConfirm;

        @AssertTrue
        public boolean isPasswordEquals ()
        {
                if ( password.isEmpty() || passwordConfirm.isEmpty() )
                        return false;

                return password.equals( passwordConfirm );
        }
}
