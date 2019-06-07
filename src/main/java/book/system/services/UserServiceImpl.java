package book.system.services;

import book.system.dto.UserDTO;
import book.system.models.User;
import book.system.models.UserRole;
import book.system.repositories.UserRepository;
import book.system.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service ("userService")
public class UserServiceImpl implements UserService
{
        private final UserRepository userRepository;

        private final BCryptPasswordEncoder encoder;

        private final UserRoleRepository roleRepository;

        @Autowired
        public UserServiceImpl ( UserRepository userRepository, BCryptPasswordEncoder encoder, UserRoleRepository roleRepository )
        {
                this.userRepository = userRepository;
                this.encoder = encoder;
                this.roleRepository = roleRepository;
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
        public UserDTO create ( UserDTO userDTO )
        {
                if ( userDTO != null )
                {
                        if ( userRepository.findByUsername( userDTO.getUsername() ) != null )
                                throw new RuntimeException( "Username is already taken" );
                        userRepository.save(
                                User.builder()
                                        .username( userDTO.getUsername() )
                                        .password( encoder.encode( userDTO.getPassword() ) )
                                        .credentials( false )
                                        .enabled( true )
                                        .locked( false )
                                        .expired( false )
                                        .userRoles( Collections.singleton( roleRepository.findByUserType( UserRole.UserType.ROLE_USER ) ) )
                                        .build()
                        );
                        return userDTO;
                }
                return null;
        }

        @Override
        public boolean remove ( UserDTO user )
        {
                return false;
        }

        @Override
        public boolean removeById ( Long id )
        {
                return false;
        }

}
