package com.example.demo.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SessionController.class)
class SessionControllerTest extends ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

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
}