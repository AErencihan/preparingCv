package com.example.preparingcv.service;

import com.example.preparingcv.repository.UserAboutRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAboutService {

    private final UserAboutRepository aboutRepository;

    public UserAboutService(UserAboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;

    }
}
