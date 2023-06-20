package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

  private final AccessTokenService accessTokenService;

  private static final String TOKEN_PREFIX = "Bearer ";

  public AccessTokenAuthenticationFilter(AccessTokenService accessTokenService) {
    this.accessTokenService = accessTokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // request에서 토큰을 파싱해와서
    String accessToken = parseAccessToken(request);

    // 토큰을 인증하고
    Authentication authentication = accessTokenService.authenticate(accessToken);

    // SecurityContext에 담는다.
    SecurityContextHolder.getContext()
        .setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }

  private String parseAccessToken(HttpServletRequest request) {
    // request에서 Authorization 꺼내서
    String authorizationToken = Optional.ofNullable(request.getHeader("Authorization"))
        .filter(s -> s.startsWith(TOKEN_PREFIX)) // Bearer로 시작하는 부분 가져와서
        .map(s -> s.substring(TOKEN_PREFIX.length())) // 앞에 'Bearer'는 떼서 리턴
        .orElse("");
    return authorizationToken;
  }
}
