package com.example.demo.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.infrastructure.ProductSummaryDtoFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductListServiceTest {

  private GetProductListService getProductListService;

  private ProductSummaryDtoFetcher productSummaryDtoFetcher;

  @BeforeEach
  void setUp() {
    productSummaryDtoFetcher = mock(ProductSummaryDtoFetcher.class);
    getProductListService = new GetProductListService(
        productSummaryDtoFetcher);
  }


  @Test
  void getProductListWithoutCategory() {
    String categoryId = "TEST";
    getProductListService.getProductListDto(categoryId);

    verify(productSummaryDtoFetcher).fetchProductSummaryDto(categoryId);
  }
}