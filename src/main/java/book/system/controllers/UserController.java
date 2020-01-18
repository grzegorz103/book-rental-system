package book.system.controllers;

import book.system.dto.UserDTO;
import book.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public boolean delete(@RequestBody UserDTO userDTO) {
        return userService.delete(userDTO);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public boolean deleteByID(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public Boolean hasAdminRole() {
        return userService.hasAdminRole();
    }

    @RequestMapping("/login")
    @PreAuthorize("isAnonymous()")
    public boolean login(@Valid @RequestBody UserDTO userDTO) {
        return userService.isLoginCorrect(userDTO.getUsername(), userDTO.getPassword());
    }
}
