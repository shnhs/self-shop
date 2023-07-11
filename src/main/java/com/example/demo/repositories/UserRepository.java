package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
}
