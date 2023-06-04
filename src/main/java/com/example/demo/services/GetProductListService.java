package com.example.demo.services;

import com.example.demo.dtos.ProductListDto;
import com.example.demo.infrastructure.ProductSummaryDtoFetcher;
import org.springframework.stereotype.Service;

@Service
public class GetProductListService {

  private final ProductSummaryDtoFetcher productSummaryDtoFetcher;

  public GetProductListService(ProductSummaryDtoFetcher productSummaryDtoFetcher) {
    this.productSummaryDtoFetcher = productSummaryDtoFetcher;
  }

  public ProductListDto getProductListDto(String categoryId) {

    return productSummaryDtoFetcher.fetchProductSummaryDto(categoryId);
  }
}
