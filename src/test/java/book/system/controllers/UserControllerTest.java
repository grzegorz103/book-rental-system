package book.system.controllers;

import book.system.dto.UserDTO;
import book.system.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(locations="classpath:application-dev.properties")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUserTest() throws Exception {

        final UserDTO testUser = UserDTO.builder()
            .username("testUser")
            .password("testPass")
            .passwordConfirm("testPass")
            .build();

        when(userService.create(testUser)).thenReturn(testUser);
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/api/user")
            .with(user("admin").password("admin").roles("ADMIN"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testUser)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value(testUser.getUsername()));
    }

    @Test
    public void deleteUserByIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/user/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .with(user("admin").password("admin").roles("ADMIN"))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

}
