package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.model.User;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAboutServiceTest {

    private final UserAboutRepository userAboutRepository = mock(UserAboutRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserAboutService userAboutService = new UserAboutService(userAboutRepository, userRepository);


    @Test
    void createOrUpdateUserAbout() {
        Long userAboutId = 1L;
        User user = new User();
        user.setId(1L);

        UserAboutRequest request = new UserAboutRequest(userAboutId, "01-01-2000", "05555555555",
                "istanbul", user.getId());

        UserAbout savedUserAbout = new UserAbout(user, request.getBirthDay(), request.getPhoneNumber(), request.getAddress());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userAboutRepository.save(any(UserAbout.class))).thenReturn(savedUserAbout);

        UserAboutDto result = userAboutService.createOrUpdateUserAbout(request);

        assertNotNull(result);
        assertEquals(request.getBirthDay(), result.getBirthDay());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(request.getAddress(), result.getAddress());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userAboutRepository, times(1)).save(any(UserAbout.class));

    }

    @Test
    void getUserAbout() {
        Long userAboutId = 1L;
        UserAbout userAbout = new UserAbout(null, "01-01-2000", "05555555555",
                "istanbul");

        when(userAboutRepository.findById(userAboutId)).thenReturn(Optional.of(userAbout));

        UserAboutDto result = userAboutService.getUserAbout(userAboutId);

        assertNotNull(result);
        assertEquals(userAbout.getBirthDay(), result.getBirthDay());
        assertEquals(userAbout.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(userAbout.getAddress(), result.getAddress());

        verify(userAboutRepository, times(1)).findById(userAboutId);
    }

    @Test
    void deleteUserAbout(){
        Long userAboutId = 1L;
        when(userAboutRepository.existsById(userAboutId)).thenReturn(true);
        userAboutService.deleteUserAbout(userAboutId);
        verify(userAboutRepository, times(1)).deleteById(userAboutId);

    }

}