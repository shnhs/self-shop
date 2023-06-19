package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenGenerator {

  private final Algorithm algorithm;

  public AccessTokenGenerator(
      @Value("${jwt.secret}")
      String secret
  ) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  public boolean verify(String accessToken) {
    try {
      JWTVerifier verifier = JWT.require(algorithm).build();
      verifier.verify(accessToken); // 토큰 인증해봐서 되면 true
      return true;
    } catch (JWTVerificationException e) {
      return false; // 인증 실패 시 false
    }
  }

  public String generate(String userId) {
    String token = JWT.create()
        .withClaim("userId", userId) // 입력된 userId로 토큰 생성
        .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS)) // 만료기한 24시간
        .sign(algorithm); // 지정된 secret 사용하여 싸인
    return token;
  }
}
