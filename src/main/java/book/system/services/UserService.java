package book.system.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends UserDetails
{
        User create ( User user );


}
