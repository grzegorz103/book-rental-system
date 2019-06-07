package book.system.mappers;

import book.system.dto.UserDTO;
import book.system.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
        public User DTOtoUser ( UserDTO userDTO )
        {
                if ( userDTO == null )
                {
                        return null;
                } else
                {
                        User user = new User();
                        user.setId( userDTO.getId() );
                        user.setUsername( userDTO.getUsername() );
                        user.setPassword( userDTO.getPassword() );
                        return user;
                }
        }

        public UserDTO userToDTO ( User user )
        {
                if ( user == null )
                {
                        return null;
                } else
                {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setId( user.getId() );
                        userDTO.setPassword( user.getPassword() );
                        userDTO.setPasswordConfirm( user.getPassword() );
                        return userDTO;
                }
        }
}
