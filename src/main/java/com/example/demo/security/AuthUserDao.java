package com.example.demo.security;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AuthUserDao {

  public Optional<AuthUser> findByAccessToken(String accessToken) {
    // TODO 구현
    return null;
  }
}
