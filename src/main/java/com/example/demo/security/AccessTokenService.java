package com.example.demo.security;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenService {

  private final AccessTokenGenerator accessTokenGenerator;
  private final AuthUserDao authUserDao;

  public AccessTokenService(AccessTokenGenerator accessTokenGenerator,
      AuthUserDao authUserDao) {
    this.accessTokenGenerator = accessTokenGenerator;
    this.authUserDao = authUserDao;
  }

  /**
   * AccessToken을 입력받아 인증처리를 진행하는 메서드
   *
   * @param accessToken
   * @return Authentication 객체. 인증이 실패 시 null
   */
  public Authentication authenticate(String accessToken) {
    // 토큰 인증. 인증이 실패하면 null 반환
    if (!accessTokenGenerator.verify(accessToken)) {
      return null;
    }

    // 인증 성공시 Auth객체 반환
    return authUserDao.findByAccessToken(accessToken)
        .map(authUser ->
            UsernamePasswordAuthenticationToken.authenticated(
                // Principal로 AuthUser 객체를 그대로 활용한다.
                authUser, null, List.of(authUser::role)))
        .orElse(null);
  }
}