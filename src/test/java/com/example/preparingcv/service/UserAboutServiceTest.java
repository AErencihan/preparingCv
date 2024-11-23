package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.model.User;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAboutServiceTest {

    private final UserAboutRepository userAboutRepository = mock(UserAboutRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserAboutService userAboutService = new UserAboutService(userAboutRepository, userRepository);


    @Test
    void createOrUpdateUserAbout(){

        User user = new User();
        user.setId(1L);

        UserAboutRequest request = new UserAboutRequest(user.getId(), "01-01-2000", "05555555555",
                "istanbul", user.getId());

        UserAbout savedUserAbout =  new UserAbout(user, request.getBirthDay(), request.getPhoneNumber(), request.getAddress());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userAboutRepository.save(any(UserAbout.class))).thenReturn(savedUserAbout);

        UserAboutDto result = userAboutService.createOrUpdateUserAbout(request);

        assertNotNull(result);
        assertNotEquals("eşleşmeyen doğum günü",request.getBirthDay(), result.getBirthDay());
        assertNotEquals("eşleşmeyen telefon numarası ",request.getPhoneNumber(), result.getPhoneNumber());
        assertNotEquals("eşleşmeyen adres", request.getAddress(), result.getAddress());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userAboutRepository, times(1)).save(any(UserAbout.class));

    }

    @Test
    void getUserAbout(){
        Long userAboutId = 1L;
        UserAbout userAbout = new UserAbout(null, "01-01-2000", "05555555555",
                "istanbul");

        when(userAboutRepository.findById(userAboutId)).thenReturn(Optional.of(userAbout));

        UserAboutDto result = userAboutService.getUserAbout(userAboutId);

        assertNotNull(result);
        assertNotEquals("eşleşmeyen doğum günü",userAbout.getBirthDay(), result.getBirthDay());
        assertNotEquals("eşleşmeyen telefon numarası ",userAbout.getPhoneNumber(), result.getPhoneNumber());
        assertNotEquals("eşleşmeyen adres", userAbout.getAddress(), result.getAddress());

        verify(userAboutRepository, times(1)).findById(userAboutId);



    }

}