package com.example.demo.dtos;

import com.example.demo.dtos.CategoryListDto.CategoryDto;
import com.example.demo.dtos.ProductSummaryDto.ImageDto;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import java.util.List;

public record ProductDetailDto(
    String id,
    CategoryDto category,
    List<ImageDto> images,
    String name,
    Long price,
    List<ProductOptionDto> options,
    String description
) {


  public static ProductDetailDto of(Product product, Category category) {
    return new ProductDetailDto(
        product.id()
               .toString(),
        CategoryDto.of(category),
        product.images()
               .stream()
               .map(ImageDto::of)
               .toList(),
        product.name(),
        product.price()
               .asLong(),
        product.options()
               .stream()
               .map(ProductOptionDto::of)
               .toList(),
        product.description()
    );
  }
}
