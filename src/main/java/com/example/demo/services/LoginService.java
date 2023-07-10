package com.example.demo.services;

import com.example.demo.security.AccessTokenGenerator;
import com.example.demo.security.AuthUserDao;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final AuthUserDao authUserDao;
  private final AccessTokenGenerator accessTokenGenerator;
  private final PasswordEncoder passwordEncoder;

  public LoginService(AuthUserDao authUserDao, AccessTokenGenerator accessTokenGenerator,
                      PasswordEncoder passwordEncoder) {
    this.authUserDao = authUserDao;
    this.accessTokenGenerator = accessTokenGenerator;
    this.passwordEncoder = passwordEncoder;
  }

  public String login(String email, String password) {

    return authUserDao.findByEmail(email)
        .filter(authUser -> passwordEncoder.matches(password, authUser.password()))
        .map(authUser -> {
          String id = authUser.id();
          String token = accessTokenGenerator.generate(id);
          authUserDao.addAccessToken(id, token);
          return token;
        }).orElseThrow(() -> new BadCredentialsException("Login Failed!"));
  }
}
