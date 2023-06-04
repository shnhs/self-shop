package com.example.demo.dtos;

import com.example.demo.models.Category;
import java.util.List;

public record CategoryListDto(
    List<CategoryDto> categories
) {

  public record CategoryDto(
      String id,
      String name
  ) {

    public static CategoryDto of(Category category) {
      return new CategoryDto(category.id()
                                     .toString(), category.name());
    }
  }
}
