package book.system.controllers;

import book.system.dto.UserDTO;
import book.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        public UserDTO create ( @RequestBody UserDTO userDTO )
        {
                return userService.create( userDTO );
        }

        @DeleteMapping
        public boolean delete ( @RequestBody UserDTO userDTO )
        {
                return userService.delete( userDTO );
        }

        @DeleteMapping ("/{id}")
        public boolean deleteByID ( @PathVariable ("id") Long id )
        {
                return userService.deleteById( id );
        }
}
