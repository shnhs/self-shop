package com.example.demo.controllers;

import com.example.demo.dtos.LoginRequestDto;
import com.example.demo.dtos.LoginResultDto;
import com.example.demo.security.AuthUser;
import com.example.demo.services.LoginService;
import com.example.demo.services.LogoutService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

  private final LoginService loginService;

  private final LogoutService logoutService;

  public SessionController(LoginService loginService,
                           LogoutService logoutService) {
    this.loginService = loginService;
    this.logoutService = logoutService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(@RequestBody LoginRequestDto loginRequestDto) {
    String token = loginService.login(loginRequestDto.email(), loginRequestDto.password());
    return new LoginResultDto(token);
  }

  @DeleteMapping
  public String logout(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();

    logoutService.logout(authUser.accessToken());

    return "Logout";
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() { // 로그인 실패시
    return "Bad Request";
  }
}
