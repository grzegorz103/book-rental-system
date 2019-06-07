package book.system.services;

import book.system.dto.UserDTO;
import book.system.mappers.UserMapper;
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

        private final UserMapper userMapper;

        @Autowired
        public UserServiceImpl ( UserRepository userRepository, BCryptPasswordEncoder encoder, UserRoleRepository roleRepository, UserMapper userMapper )
        {
                this.userRepository = userRepository;
                this.encoder = encoder;
                this.roleRepository = roleRepository;
                this.userMapper = userMapper;
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
                        User user = userMapper.DTOtoUser( userDTO );
                        user.setPassword( encoder.encode( user.getPassword() ) );
                        user.setCredentials( false );
                        user.setEnabled( true );
                        user.setExpired( false );
                        user.setLocked( false );
                        user.setUserRoles( Collections.singleton( roleRepository.findByUserType( UserRole.UserType.ROLE_USER ) ) );

                        return userMapper.userToDTO( userRepository.save( user ) );
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