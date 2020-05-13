package book.system.services;

import book.system.dto.UserDTO;
import book.system.mappers.UserMapper;
import book.system.models.User;
import book.system.models.UserRole;
import book.system.repositories.UserRepository;
import book.system.repositories.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations="classpath:application-dev.properties")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRoleRepository roleRepository;

    @Spy
    private UserMapper userMapper;

    @Spy
    private BCryptPasswordEncoder encoder;

    private UserDTO test;

    @Before
    public void setup() {
        test = UserDTO.builder().id(1L).username("testUser").password("testPass").passwordConfirm("testPass").build();
    }

    @Test
    public void loadByUsernameTest() {
        User test = User.builder().username("testName").password("testPass").build();
        when(userRepository.findByUsername("testName")).thenReturn(test);

        assertThat(userService.loadUserByUsername("testName")).isEqualTo(test);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsernameExceptionTest() {
        when(userRepository.findByUsername("testName")).thenReturn(null);
        userService.loadUserByUsername("testName");
    }

    @Test
    public void createUserTest() {
        when(roleRepository.findByUserType(UserRole.UserType.ROLE_USER)).thenReturn(new UserRole(1L, UserRole.UserType.ROLE_USER));
        test = UserDTO.builder().username("testUser").password("testPass").passwordConfirm("testPass").build();

        userService.create(test);

        verify(userRepository).save(any(User.class));
        verify(encoder).encode(anyString());
        verify(roleRepository).findByUserType(any(UserRole.UserType.class));
    }

    @Test
    public void deleteUserTest() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        assertTrue(userService.delete(test));
        assertFalse(userService.delete(null));
        verify(userRepository).delete(any(User.class));
    }

    @Test
    public void deleteUserByIdTest() {
        when(userRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(userService.deleteById(1L));
        verify(userRepository).deleteById(anyLong());
        assertFalse(userService.deleteById(null));
    }
}
