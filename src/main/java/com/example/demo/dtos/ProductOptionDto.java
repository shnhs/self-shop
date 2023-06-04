package com.example.demo.dtos;

import com.example.demo.models.ProductOption;
import java.util.List;

public record ProductOptionDto(
    String id,
    String name, // 옵션 이름
    List<ProductOptionItemDto> items
) {


  public static ProductOptionDto of(ProductOption productOption) {
    return new ProductOptionDto(
        productOption.id()
                     .toString(),
        productOption.name(),
        productOption.items()
                     .stream()
                     .map(ProductOptionItemDto::of)
                     .toList());
  }
}
