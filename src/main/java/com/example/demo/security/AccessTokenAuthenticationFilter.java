package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

  private static final String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // request에서 토큰을 파싱해와서
    String accessToken = parseAccessToken(request);

    // 토큰을 인증하고 TODO 토큰 서비스 만들어서 인증 처리
    Authentication authentication =
        new UsernamePasswordAuthenticationToken("User", null, List.of());

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
