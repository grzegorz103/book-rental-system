package book.system.services;

import book.system.dto.UserDTO;
import book.system.mappers.UserMapper;
import book.system.models.User;
import book.system.models.UserRole;
import book.system.repositories.UserRepository;
import book.system.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.http.SecurityHeaders;
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
        public UserServiceImpl ( UserRepository userRepository,
                                 BCryptPasswordEncoder encoder,
                                 UserRoleRepository roleRepository,
                                 UserMapper userMapper )
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
        public boolean delete ( UserDTO userDTO )
        {
                if ( userDTO != null )
                {
                        User mapped = userMapper.DTOtoUser( userDTO );
                        if ( userRepository.existsById( mapped.getId() ) )
                        {
                                userRepository.delete( mapped );
                                return true;
                        }
                }
                return false;
        }

        @Override
        public boolean deleteById ( Long id )
        {
                if ( id != null && userRepository.existsById( id ) )
                {
                        userRepository.deleteById( id );
                        return true;
                }
                return false;
        }

        @Override
        public boolean hasAdminRole ()
        {
                return (( User ) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                ).getUserRoles()
                        .stream()
                        .anyMatch( e -> e.getUserType() == UserRole.UserType.ROLE_ADMIN );
        }

        @Override
        public boolean isLoginCorrect ( String username, String password )
        {
                User u = userRepository.findByUsername( username );
                if ( u == null )
                {
                        return false;
                }

                return u.getUsername().equals( username )
                        && encoder.matches( password, u.getPassword() );

        }

}
