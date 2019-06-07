package book.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
        private Long id;
        private String username;
        private String password;
        private String passwordConfirm;

        @AssertTrue
        public boolean isPasswordEquals ()
        {
                if ( password.isEmpty() || passwordConfirm.isEmpty() )
                        return false;

                return password.equals( passwordConfirm );
        }
}
