package com.example.demo.repositories;

import com.example.demo.models.CategoryId;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {

  List<Product> findByCategoryId(CategoryId categoryId);
}
