package com.example.demo.security;

public record AuthUser(
    String id,
    String email,
    String password,
    String role,
    String accessToken
) {

  public static AuthUser authenticated(String userId,
      String roleUser, String userAccessToken) {
    return new AuthUser(userId, "", "", roleUser, userAccessToken);
  }

  public static AuthUser of(
      String id, String email, String password, String role) {
    return new AuthUser(id, email, password, role, "");
  }
}
