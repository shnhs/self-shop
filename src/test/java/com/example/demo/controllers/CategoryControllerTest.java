package com.example.demo.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.services.GetCategoryListService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetCategoryListService getCategoryListService;

  @Test
  @DisplayName("GET /categories")
  void list()
      throws Exception {

    Category category = new Category(new CategoryId("TEST"), "top");
    given(getCategoryListService.getCategoryList()).willReturn(List.of(category));

    mockMvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("top")));
  }

}