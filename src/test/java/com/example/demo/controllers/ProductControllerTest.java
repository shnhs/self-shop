package com.example.demo.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dtos.CategoryListDto.CategoryDto;
import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.dtos.ProductOptionDto;
import com.example.demo.dtos.ProductOptionItemDto;
import com.example.demo.dtos.ProductSummaryDto;
import com.example.demo.dtos.ProductSummaryDto.ImageDto;
import com.example.demo.services.GetProductDetailService;
import com.example.demo.services.GetProductListService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends ControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetProductListService getProductListService;

  @MockBean
  private GetProductDetailService getProductDetailService;

  @Test
  @DisplayName("GET /products")
  void getProducts()
      throws Exception {
    ProductSummaryDto productDto = new ProductSummaryDto("PRODUCT", new CategoryDto("CAT", "top"),
        new ImageDto("URL"), "PRODUCT", 100000L);
    String categoryId = null;

    given(getProductListService.getProductListDto(categoryId)).willReturn(new ProductListDto(
        List.of(productDto)));

    mockMvc.perform(get("/products"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("PRODUCT")));
  }

  @Test
  @DisplayName("GET /products/{id}")
  void getProductDetail()
      throws Exception {
    String productId = "NICE_PRODUCT";
    CategoryDto category = new CategoryDto("CATE01", "top");
    List<ImageDto> images = List.of(new ImageDto("URL"));
    List<ProductOptionDto> options = List.of(
        new ProductOptionDto("OPTION1", "색상",
            List.of(new ProductOptionItemDto("ITEM1", "RED"))));

    given(getProductDetailService.getProductDetail(productId)).willReturn(
        new ProductDetailDto(productId, category, images, "nice product",
            90L, options, "멋져용"));

    mockMvc.perform(get("/products/%s".formatted(productId)))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("nice product")));
  }
}