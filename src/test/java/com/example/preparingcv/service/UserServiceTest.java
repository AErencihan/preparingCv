package com.example.preparingcv.service;


import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void createUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("aa", result.getName());
        assertEquals("aaa", result.getSurName());
        assertEquals("aa", result.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUser() {
        String userName = "aa";

        User user = new User();
        user.setUserName(userName);
        user.setUserSurname("cihan");

        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));

        UserDto result = userService.getUser(userName);

        assertNotNull(result);
        assertEquals("aa", result.getName());
        assertEquals("cihan", result.getSurName());

        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals("aa", result.getName());
        assertEquals("aaa", result.getSurName());
        assertEquals("aa", result.getEmail());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }


}