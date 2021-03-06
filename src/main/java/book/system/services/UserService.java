package book.system.services;

import book.system.dto.UserDTO;
import book.system.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();

    UserDTO create(UserDTO userDTO);

    boolean delete(UserDTO userDTO);

    boolean deleteById(Long id);

    boolean hasAdminRole();

    boolean isLoginCorrect(String username, String password);
}
