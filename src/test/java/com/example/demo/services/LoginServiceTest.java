package com.example.demo.services;

import com.example.demo.security.AccessTokenGenerator;
import com.example.demo.security.AuthUser;
import com.example.demo.security.AuthUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoginServiceTest {

  private LoginService loginService;
  private AuthUserDao authUserDao;

  private AccessTokenGenerator accessTokenGenerator;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setUp() {
    authUserDao = mock(AuthUserDao.class);
    accessTokenGenerator = new AccessTokenGenerator("SECRET");
    passwordEncoder = mock(PasswordEncoder.class);

    loginService = new LoginService(
        authUserDao, passwordEncoder, accessTokenGenerator);
  }

  @Test
  @DisplayName("Login - with correct Email & password")
  void loginSuccess() {
    String id = "USER_ID";
    String email = "tester@example.com";
    String password = "password";
    String encodedPassword = "ENCODED_PASSWORD";
    String role = "ROLE_USER";

    // Given
    AuthUser authUser = AuthUser.of(id, email, encodedPassword, role);

    // 주어진 이메일로 사용자 찾아짐
    given(authUserDao.findByEmail(email))
        .willReturn(Optional.of(authUser));

    // 비밀번호 일치하는 것으로 mocking
    given(passwordEncoder.matches(password, encodedPassword))
        .willReturn(true);

    // When - 이메일과 패스워드로 로그인
    String token = loginService.login(email, password);

    // Then - 토큰이 나옴
    verify(authUserDao).addAccessToken(eq(id), any());
    assertThat(token).isNotBlank();
  }

  @Test
  @DisplayName("Login - with incorrect Email")
  void loginFail1() {
    // Given
    String id = "USER_ID";
    String email = "nope@example.com";
    String password = "password";
    String encodedPassword = "ENCODED_PASSWORD";
    String role = "ROLE_USER";
    AuthUser authUser = AuthUser.of(id, email, encodedPassword, role);

    given(authUserDao.findByEmail(email))
        .willReturn(Optional.empty());

    // When + Then
    assertThatThrownBy(() -> {
      loginService.login(email, password);
    }).isInstanceOf(
        BadCredentialsException.class);
  }

  @Test
  @DisplayName("Login - with incorrect password")
  void loginFail2() {
    // Given
    String id = "USER_ID";
    String email = "nope@example.com";
    String password = "nope";
    String encodedPassword = "ENCODED_PASSWORD";
    String role = "ROLE_USER";
    AuthUser authUser = AuthUser.of(id, email, encodedPassword, role);

    given(authUserDao.findByEmail(email)).willReturn(Optional.of(authUser));
    given(passwordEncoder.matches(password, encodedPassword)).willReturn(false);

    assertThatThrownBy(() -> {
      loginService.login(email, password);
    }).isInstanceOf(BadCredentialsException.class);
  }
}