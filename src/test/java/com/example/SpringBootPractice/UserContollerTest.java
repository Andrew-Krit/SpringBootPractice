package com.example.SpringBootPractice;

import com.example.SpringBootPractice.controller.UserController;
import com.example.SpringBootPractice.dto.UserDto;
import com.example.SpringBootPractice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_shouldReturnList() throws Exception {
        List<UserDto> users = List.of(new UserDto(1,"Alice", "alice@mail.com", 12));
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    public void getUserById_shouldReturnUser() throws Exception {
        UserDto user = new UserDto(1, "Bob", "bob@mail.com", 22);
        when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.email").value("bob@mail.com"))
                .andExpect(jsonPath("$.age").value(22));
    }

    @Test
    public void createUser_shouldReturnCreatedUser() throws Exception {
        UserDto saved = new UserDto(3, "Charlie", "charlie@mail.com", 30);
        when(userService.createUser(any(UserDto.class))).thenReturn(saved);

        String json = """
        {
            "name": "Charlie",
            "email": "charlie@mail.com",
            "age": 30
        }
        """;

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Charlie"));
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        UserDto updated = new UserDto(1, "Alice Updated", "alice_updated@mail.com", 13);
        when(userService.updateUser(eq(1), any(UserDto.class))).thenReturn(updated);

        String json = """
        {
            "name": "Alice Updated",
            "email": "alice_updated@mail.com",
            "age": 13
        }
        """;

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"))
                .andExpect(jsonPath("$.email").value("alice_updated@mail.com"))
                .andExpect(jsonPath("$.age").value(13));
    }

    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(1);
    }
}
