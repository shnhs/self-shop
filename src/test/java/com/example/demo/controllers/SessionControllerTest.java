package com.example.demo.controllers;

import com.example.demo.services.LoginService;
import com.example.demo.services.LogoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest extends ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

  @MockBean
  private LogoutService logoutService;

  @BeforeEach
  void setUp() {
    given(loginService.login("tester@example", "password"))
        .willReturn(userAccessToken);
  }

  @Test
  @DisplayName("POST /session - with correct AccessToken")
  void loginWithAccessToken() throws Exception {
    String json = """
        {
          "username": "tester@example.com",
          "password": "password"
        }
        """;
    mockMvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("POST /session - with incorrect AccessToken")
  void loginWithIncorrectAccessToken() throws Exception {
    String json = """
        {
          "username": "tester@example.com",
          "password": "wrong"
        }
        """;
    mockMvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("GET /session - without AccessToken")
  void loginWithoutAccessToken() throws Exception {
    String json = """
        {
          "username": "nope",
          "password": "password"
        }
        """;
    mockMvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("DELETE /session - with correct access token")
  void logoutWithCorrectAccessToken() throws Exception {
    mockMvc.perform(delete("/session")
            .header("Authorization", "Bearer " + userAccessToken))
        .andExpect(status().isOk());

    verify(logoutService).logout(userAccessToken);
  }

  @Test
  @DisplayName("DELETE /session - with incorrect access token")
  void logoutWithIncorrectAccessToken() throws Exception {
    mockMvc.perform(delete("/session")
            .header("Authorization", "Bearer XXX"))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("DELETE /session - without access token")
  void logoutWithoutAccessToken() throws Exception {
    mockMvc.perform(delete("/session"))
        .andExpect(status().isForbidden());
  }
}