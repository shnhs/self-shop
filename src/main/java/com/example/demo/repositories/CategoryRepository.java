package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

}
