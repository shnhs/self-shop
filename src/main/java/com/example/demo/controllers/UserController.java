package com.example.demo.controllers;

import com.example.demo.dtos.SignupRequestDto;
import com.example.demo.dtos.SignupResultDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;
import com.example.demo.services.GetUserService;
import com.example.demo.services.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private final GetUserService getUserService;
  private final SignupService signupService;

  public UserController(GetUserService getUserService,
                        SignupService signupService) {
    this.getUserService = getUserService;
    this.signupService = signupService;
  }

  @GetMapping("/me")
  public UserDto me(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UserId id = new UserId(authUser.id());
    User user = getUserService.getUser(id);
    return UserDto.of(user);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SignupResultDto signup(
      @Valid @RequestBody SignupRequestDto signupRequestDto
  ) {
    String accessToken = signupService.signup(
        signupRequestDto.email().trim(),
        signupRequestDto.name().trim(),
        signupRequestDto.password().trim()
    );

    return new SignupResultDto(accessToken);
  }
}