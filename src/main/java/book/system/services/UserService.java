package book.system.services;

import book.system.dto.UserDTO;
import book.system.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
        UserDTO create ( UserDTO userDTO );

        boolean delete ( UserDTO userDTO );

        boolean deleteById ( Long id );
}
