package com.example.SpringBootPractice.userService.repository;

import com.example.SpringBootPractice.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }
