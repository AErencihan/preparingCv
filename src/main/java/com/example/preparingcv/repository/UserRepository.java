package com.example.preparingcv.repository;

import com.example.preparingcv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
