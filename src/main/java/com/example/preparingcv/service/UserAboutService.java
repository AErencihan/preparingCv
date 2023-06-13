package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAboutService {

    private final UserAboutRepository aboutRepository;

    public UserAboutService(UserAboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public UserAboutDto createUserAbout(UserAbout userAbout) {
        var saveUserAbout = aboutRepository.save(userAbout);

        return new UserAboutDto.Builder()
                .address(saveUserAbout.getAddress())
                .birthDay(saveUserAbout.getBirthDay())
                .phoneNumber(saveUserAbout.getPhoneNumber())
                .build();
    }

    public UserAbout getUserAbout(Long userAboutId){
        return aboutRepository.findById(userAboutId).orElseThrow(()-> new GenericException.Builder()
                .message("no information about the user")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
    }


    public UserAboutDto updateUserAbout(UserAbout userAbout) {
        aboutRepository.findById(userAbout.getUserAboutId())
                .orElseThrow(()-> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("no information for update")
                        .build());

        UserAbout saved = aboutRepository.save(userAbout);

        return new UserAboutDto.Builder()
                .phoneNumber(saved.getPhoneNumber())
                .address(saved.getAddress())
                .birthDay(saved.getBirthDay())
                .build();
    }

    public void deleteUserAbout(Long userAboutId) {
        boolean exists = aboutRepository.existsById(userAboutId);

        Optional.of(exists).ifPresentOrElse(a-> aboutRepository.deleteById(userAboutId), ()->{
            new GenericException.Builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("No userAbout for delete")
                    .build();
        });
    }

}







