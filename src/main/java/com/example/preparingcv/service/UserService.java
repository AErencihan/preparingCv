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

    public UserDto createUser(User user) {
        User saveUser = userRepository.save(user);
        return new UserDto.Builder()
                .eMail(saveUser.geteMail())
                .name(saveUser.getUserName())
                .surname(saveUser.getUserName())
                .build();
    }

    public User findUserByName(String name) {
        return userRepository.findByUserName(name).orElseThrow();
    }

    public User updateUser(Long id, User user) throws Exception {
        User updateUser = userRepository.findById(id)
                .orElseThrow(()-> new Exception("not found"));

        updateUser.setUserName(user.getUserName());
        updateUser.setUserSurname(user.getUserSurname());
        updateUser.setUserAbout(user.getUserAbout());

        return userRepository.save(updateUser);


    }


}
