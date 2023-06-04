package com.example.demo.services;

import static org.mockito.Mockito.mock;

import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductDetailServiceTest {

  private GetProductDetailService getProductDetailService;
  private ProductRepository productRepository;
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    categoryRepository = mock(CategoryRepository.class);
    getProductDetailService = new GetProductDetailService(productRepository, categoryRepository);
  }

  @Test
  void getProductDetail() {
//    String productId = "TEST";
//
//    given(productRepository.findById(new ProductId(productId))).willReturn(
//        Optional.of(new Product()));
//
//    getProductDetailService.getProductDetail(productId);
//
//    verify(productRepository).findById(new ProductId(productId));
  }
}