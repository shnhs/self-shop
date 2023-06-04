package com.example.demo.controllers;

import com.example.demo.dtos.CategoryListDto;
import com.example.demo.dtos.CategoryListDto.CategoryDto;
import com.example.demo.models.Category;
import com.example.demo.services.GetCategoryListService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

  private final GetCategoryListService getCategoryListService;

  public CategoryController(GetCategoryListService getCategoryListService) {
    this.getCategoryListService = getCategoryListService;
  }

  @GetMapping
  public CategoryListDto list() {

    List<Category> categoryList = getCategoryListService.getCategoryList();

    return new CategoryListDto(categoryList.stream()
                                           .map(category -> toDto(category))
                                           .toList());
  }

  private CategoryDto toDto(Category category) {
    return new CategoryDto(category.id()
                                   .toString(), category.name());
  }

}
