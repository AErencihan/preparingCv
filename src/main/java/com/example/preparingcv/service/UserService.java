package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(User user){






    }


}
