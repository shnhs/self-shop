package com.example.demo.controllers;

import static org.mockito.BDDMockito.given;

import com.example.demo.DemoApplication;
import com.example.demo.config.WebSecurityConfig;
import com.example.demo.security.AccessTokenGenerator;
import com.example.demo.security.AccessTokenService;
import com.example.demo.security.AuthUser;
import com.example.demo.security.AuthUserDao;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {DemoApplication.class, WebSecurityConfig.class})
public abstract class ControllerTest {

  protected static final String USER_ID = "UserId";
  protected static final String ADMIN_ID = "AdminId";

  @SpyBean
  private AccessTokenService accessTokenService;

  @SpyBean
  private AccessTokenGenerator accessTokenGenerator;

  @MockBean
  protected AuthUserDao authUserDao;

  protected String userAccessToken;
  protected String adminAccessToken;


  /**
   * 매 테스트 전에 인증 처리 및 토큰 발급해놓기
   */
  @BeforeEach
  void setUpAccessTokenAndUserDetailsDaoForAuthentication() {
    // 아이디로 토큰 생성
    userAccessToken = accessTokenGenerator.generate(USER_ID);
    adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);

    // 생성한 토큰으로 AuthUser 객체 생성
    AuthUser user = AuthUser.authenticated(
        USER_ID, "ROLE_USER", userAccessToken);

    AuthUser admin = AuthUser.authenticated(
        ADMIN_ID, "ROLE_ADMIN", adminAccessToken);

    // 토큰으로 찾을 때 알맞은 유저가 반환되도록 모킹
    given(authUserDao.findByAccessToken(userAccessToken))
        .willReturn(Optional.of(user));

    given(authUserDao.findByAccessToken(adminAccessToken))
        .willReturn(Optional.of(admin));
  }
}
