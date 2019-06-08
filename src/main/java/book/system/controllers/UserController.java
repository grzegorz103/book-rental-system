package book.system.controllers;

import book.system.dto.UserDTO;
import book.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/api/user")
public class UserController
{
        private final UserService userService;

        @Autowired
        public UserController ( UserService userService )
        {
                this.userService = userService;
        }

        @PostMapping
        @Secured ("ROLE_ADMIN")
        public UserDTO create ( @Valid @RequestBody UserDTO userDTO )
        {
                return userService.create( userDTO );
        }

        @DeleteMapping
        @Secured ("ROLE_ADMIN")
        public boolean delete ( @RequestBody UserDTO userDTO )
        {
                return userService.delete( userDTO );
        }

        @DeleteMapping ("/{id}")
        @Secured ("ROLE_ADMIN")
        public boolean deleteByID ( @PathVariable ("id") Long id )
        {
                return userService.deleteById( id );
        }
}
