package com.example.demo.config;

import com.example.demo.security.AccessTokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  private final AccessTokenAuthenticationFilter accessTokenAuthenticationFilter;

  public WebSecurityConfig(AccessTokenAuthenticationFilter accessTokenAuthenticationFilter) {
    this.accessTokenAuthenticationFilter = accessTokenAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
      throws Exception {
    http.cors();
    http.csrf().disable();

    // 직접 만든 필터 추가함
    http.addFilterBefore(accessTokenAuthenticationFilter, BasicAuthenticationFilter.class);

    // 일부 API는 인증인가 없이 접근
    http.authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/session").permitAll() //로그인
        .requestMatchers(HttpMethod.POST, "/users").permitAll() // 회원가입
        .requestMatchers(HttpMethod.GET, "/backdoor/**").permitAll() // 백도어
        .requestMatchers(HttpMethod.GET, "/categories").permitAll() // 카테고리 보기
        .requestMatchers(HttpMethod.GET, "/products/**").permitAll() // 물건 보기
        .anyRequest().authenticated();

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // 비밀번호 암호화에 사용될 인코더
    return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
  }
}
