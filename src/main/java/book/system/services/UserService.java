package book.system.services;

import book.system.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
        User create ( User user );

        boolean remove ( User user );

        boolean removeById ( Long id );
}
