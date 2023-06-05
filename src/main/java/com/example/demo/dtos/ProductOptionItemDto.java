package com.example.demo.dtos;

import com.example.demo.models.ProductOptionItem;

public record ProductOptionItemDto(
    String id,
    String name
) {

  public static ProductOptionItemDto of(ProductOptionItem productOptionItem) {
    return new ProductOptionItemDto(
        productOptionItem.id()
            .toString(),
        productOptionItem.name()
    );
  }
}
