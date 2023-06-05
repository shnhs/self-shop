package com.example.demo.infrastructure;

import com.example.demo.dtos.CategoryListDto.CategoryDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.dtos.ProductSummaryDto;
import com.example.demo.dtos.ProductSummaryDto.ImageDto;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductSummaryDtoFetcher {

  private final JdbcTemplate jdbcTemplate;

  public ProductSummaryDtoFetcher(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public ProductListDto fetchProductSummaryDto(String categoryId) {
    String sql = """
        select
          p.id,
          c.id as category_id,
          c."name" as category_name,
          i.url,
          p."name" ,
          p.price
        from products p
        join (select
          distinct on(product_id) product_id, url
          from images) i on p.id = i.product_id
        join categories c on p.category_id = c.id
        """;

    if (categoryId != null) {
      sql += "where p.category_id = '%s'".formatted(categoryId);
    }

    List<ProductSummaryDto> productSummaryDtos = jdbcTemplate.query(sql, (rs, rowNum)
        -> new ProductSummaryDto(
        rs.getString("id"),
        new CategoryDto(
            rs.getString("category_id"),
            rs.getString("category_name")),
        new ImageDto(rs.getString("url")),
        rs.getString("name"),
        Long.parseLong(rs.getString("price"))
    ));

    return new ProductListDto(productSummaryDtos);
  }
}
