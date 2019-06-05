package book.system.services;

import book.system.models.User;
import book.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service ("userService")
public class UserServiceImpl implements UserService
{
        private final UserRepository userRepository;

        @Autowired
        public UserServiceImpl ( UserRepository userRepository )
        {
                this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername ( String s ) throws UsernameNotFoundException
        {
                User user = userRepository.findByUsername( s );
                if ( user == null )
                        throw new UsernameNotFoundException( "User does not exists" );
                return user;
        }

        @Override
        public User create ( User user )
        {
                return null;
        }

        @Override
        public boolean remove ( User user )
        {
                return false;
        }

        @Override
        public boolean removeById ( Long id )
        {
                return false;
        }

}
