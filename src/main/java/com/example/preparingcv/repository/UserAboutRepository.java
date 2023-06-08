package com.example.preparingcv.repository;

import com.example.preparingcv.model.UserAbout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAboutRepository extends JpaRepository<UserAbout, Long> {

}
