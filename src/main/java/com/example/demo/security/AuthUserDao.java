package com.example.demo.security;

import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthUserDao {

  private final JdbcTemplate jdbcTemplate;

  public AuthUserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<AuthUser> findByAccessToken(String accessToken) {
    // TODO 구현
    return null;
  }

  public Optional<AuthUser> findByEmail(String email) {
    String query = "SELECT id, password, role FROM users WHERE email=?";

    return jdbcTemplate.query(query, resultSet -> {
      // 없으면 empty 반환
      if (!resultSet.next()) {
        return Optional.empty();
      }
      // 이메일로 찾아지면 AuthUser 만들어서 만환
      AuthUser authUser = AuthUser.of(
          resultSet.getString("id"),
          email,
          resultSet.getString("password"),
          resultSet.getString("role")
      );

      return Optional.of(authUser);
    }, email);
  }

  public void addToken(String id, String token) {
    jdbcTemplate.update("""
            INSERT INTO access_tokens (token, user_id)
            VALUES (?, ?)
            """,
        token, id
    );
  }
}
