package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetCategoryListService {

  private final CategoryRepository categoryRepository;

  public GetCategoryListService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategoryList() {

    return categoryRepository.findAll();
  }
}
