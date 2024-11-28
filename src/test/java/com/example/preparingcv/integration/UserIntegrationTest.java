package com.example.preparingcv.integration;


import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.UserRepository;
import com.example.preparingcv.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }


    @Test
    void createUSer(){
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        UserDto createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("aa", createdUser.getName());
        assertEquals("aaa", createdUser.getSurName());
        assertEquals("aa", createdUser.getEmail());

    }

    @Test
    void deleteUser(){
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        userService.deleteUser(savedUser.getId());

        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertTrue(deletedUser.isEmpty());

    }


    @Test
    void getUser(){
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");
        userRepository.save(user);


        UserDto foundUser = userService.getUser("Jane");

        assertNotNull(foundUser);
        assertEquals("aa", foundUser.getName());
        assertEquals("aaa", foundUser.getSurName());
        assertEquals("aa", foundUser.getEmail());
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        savedUser.setUserName("bb");
        savedUser.setUserSurname("bbb");

        UserDto updatedUser = userService.updateUser(savedUser);

        assertNotNull(updatedUser);
        assertEquals("bb", updatedUser.getName());
        assertEquals("bbb", updatedUser.getSurName());
        assertEquals("aa", updatedUser.getEmail());
    }

}
