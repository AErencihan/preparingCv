package com.example.preparingcv.service;

import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getUser(String name) {
        return userRepository.findByUserName(name).orElseThrow(()-> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("User name Not found")
                .build());
    }

    public UserDto updateUser(User user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(()-> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("User not Found")
                .build());

        User saved = userRepository.save(user);

        return new UserDto.Builder()
                .surname(saved.getUserSurname())
                .name(saved.getUserName())
                .eMail(saved.geteMail())
                .build();
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);

        Optional.of(exists).ifPresentOrElse(a -> userRepository.deleteById(id),()->{
            new GenericException.Builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("no user for delete")
                    .build();
        });

    }

}








