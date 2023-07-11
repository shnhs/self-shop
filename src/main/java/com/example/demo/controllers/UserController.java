package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;
import com.example.demo.services.GetUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final GetUserService getUserService;

  public UserController(GetUserService getUserService) {
    this.getUserService = getUserService;
  }

  @GetMapping("/me")
  public UserDto me(Authentication authentication) {
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UserId id = new UserId(authUser.id());
    User user = getUserService.getUser(id);
    return UserDto.of(user);
  }
}