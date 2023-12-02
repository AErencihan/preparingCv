package com.example.preparingcv.api;

import com.example.preparingcv.dto.UserAboutDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.service.UserAboutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/userAbout")
@RestController
public class UserAboutController {
    private final UserAboutService userAboutService;

    public UserAboutController(UserAboutService userAboutService) {
        this.userAboutService = userAboutService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserAboutDto> saveUserAbout(@RequestBody UserAboutRequest userAbout){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userAboutService.createOrUpdateUserAbout(userAbout));
    }

    @DeleteMapping("/delete/{userAboutId}")
    public void deleteUserAbout(@PathVariable Long userAboutId){
        userAboutService.deleteUserAbout(userAboutId);
    }

    @PutMapping("/update")
    public UserAboutDto updateUserAbout(@RequestBody UserAboutRequest userAbout){
        return userAboutService.createOrUpdateUserAbout(userAbout);

    }

    @GetMapping("/get/{userAboutId}")
    public UserAboutDto getUserAbout(@PathVariable Long userAboutId){
        return userAboutService.getUserAbout(userAboutId);
    }


}
