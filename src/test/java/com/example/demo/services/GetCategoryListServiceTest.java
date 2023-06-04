package com.example.demo.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.repositories.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetCategoryListServiceTest {

  private GetCategoryListService getCategoryListService;
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    categoryRepository = mock(CategoryRepository.class);
    getCategoryListService = new GetCategoryListService(categoryRepository);
  }

  @Test
  void getCategoryList() {

    Category category = new Category(new CategoryId("TEST"), "top");

    given(categoryRepository.findAll()).willReturn(List.of(category));
    List<Category> categoryList = getCategoryListService.getCategoryList();

    verify(categoryRepository).findAll();
    assertThat(categoryList).hasSize(1);
  }
}