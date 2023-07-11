package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetUserService {
  private final UserRepository userRepository;

  public GetUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(UserId id) {
    return userRepository.findById(id)
        .orElseThrow();
  }
}