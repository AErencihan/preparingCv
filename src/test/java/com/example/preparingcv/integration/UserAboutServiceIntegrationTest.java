package com.example.preparingcv.integration;


import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.model.User;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import com.example.preparingcv.repository.UserRepository;
import com.example.preparingcv.service.UserAboutService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class UserAboutServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAboutService userAboutService;

    @Autowired
    private UserAboutRepository userAboutRepository;


    @Test
    void createUserAbout(){
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        UserAboutRequest request = new UserAboutRequest(null, "01.01.2000", "05555555555",
                "Istanbul", savedUser.getId());

        UserAboutDto result = userAboutService.createOrUpdateUserAbout(request);

        assertNotNull(result);
        assertEquals(request.getBirthDay(), result.getBirthDay());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(request.getAddress(), result.getAddress());

    }

    @Test
    void getUserAbout(){
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        UserAbout userAbout = new UserAbout();
        userAbout.setUser(savedUser);
        userAbout.setBirthDay("01.01.2000");
        userAbout.setPhoneNumber("05555555555");
        userAbout.setAddress("Istanbul");

        UserAbout savedUserAbout = userAboutRepository.save(userAbout);

        UserAboutDto result = userAboutService.getUserAbout(savedUserAbout.getUserAboutId());

        assertNotNull(result);
        assertEquals(savedUserAbout.getBirthDay(), result.getBirthDay());
        assertEquals(savedUserAbout.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(savedUserAbout.getAddress(), result.getAddress());

    }
}