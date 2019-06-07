package book.system.services;

import book.system.dto.UserDTO;
import book.system.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
        UserDTO create ( UserDTO userDTO );

        boolean remove ( UserDTO user );

        boolean removeById ( Long id );
}
