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
                .email(saveUser.getEmail())
                .name(saveUser.getUserName())
                .surname(saveUser.getUserName())
                .build();
    }

    public User getUser(String name) {
        return userRepository.findByUserName(name).orElseThrow(() -> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("User name Not found")
                .build());
    }

    public UserDto updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isEmpty()) {
            throw new GenericException.Builder()
                    .message("No user found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        User saved = userRepository.save(user);

        return new UserDto.Builder()
                .surname(saved.getUserSurname())
                .name(saved.getUserName())
                .email(saved.getEmail())
                .build();
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new GenericException.Builder()
                    .message("No user Delete was found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        userRepository.deleteById(id);
    }

}








