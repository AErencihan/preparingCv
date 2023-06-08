package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAboutService {

    private final UserAboutRepository aboutRepository;

    public UserAboutService(UserAboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public UserAboutDto createUserAbout(UserAbout userAbout){
        var saveUserAbout = aboutRepository.save(userAbout);

        return new UserAboutDto.Builder()
                .address(saveUserAbout.getAddress())
                .birthDay(saveUserAbout.getBirthDay())
                .phoneNumber(saveUserAbout.getPhoneNumber())
                .build();
    }



}
