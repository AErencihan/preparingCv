package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAboutService {

    private final UserAboutRepository aboutRepository;
    private final UserRepository userRepository;

    public UserAboutService(UserAboutRepository aboutRepository, UserRepository userRepository) {
        this.aboutRepository = aboutRepository;
        this.userRepository = userRepository;
    }

    public UserAboutDto createOrUpdateUserAbout(UserAboutRequest userAbout) {
        var user = userRepository.findById(userAbout.getUserId())
                .orElseThrow(() -> new GenericException.Builder()
                        .message("user not found")
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());

        UserAbout saved = aboutRepository.save(new UserAbout(
                user,
                userAbout.getBirthDay(),
                userAbout.getPhoneNumber(),
                userAbout.getAddress()
        ));

        return new UserAboutDto.Builder()
                .phoneNumber(saved.getPhoneNumber())
                .address(saved.getAddress())
                .birthDay(saved.getBirthDay())
                .build();
    }

    public UserAbout getUserAbout(Long userAboutId) {
        return aboutRepository.findById(userAboutId).orElseThrow(() -> new GenericException.Builder()
                .message("no information about the user")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
    }

    public void deleteUserAbout(Long userAboutId) {
        boolean exists = aboutRepository.existsById(userAboutId);

        if (!exists) {
            throw new GenericException.Builder()
                    .message("No user Delete was found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        aboutRepository.deleteById(userAboutId);
    }

}







