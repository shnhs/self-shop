package com.example.demo.services;

import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GetProductDetailService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public GetProductDetailService(ProductRepository productRepository,
                                 CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  public ProductDetailDto getProductDetail(String productId) {

    Product product = productRepository.findById(new ProductId(productId))
                                       .orElseThrow();
    Category category = categoryRepository.findById(product.categoryId())
                                          .orElseThrow();

    return ProductDetailDto.of(product, category);
  }
}
