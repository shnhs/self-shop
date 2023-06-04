package com.example.demo.dtos;

import com.example.demo.dtos.CategoryListDto.CategoryDto;
import com.example.demo.models.Image;

public record ProductSummaryDto(
    String id,
    CategoryDto category,
    ImageDto thumbnail,
    String name,
    Long price
) {

  public record ImageDto(
      String url
  ) {

    public static ImageDto of(Image image) {
      return new ImageDto(image.url());
    }
  }
}
