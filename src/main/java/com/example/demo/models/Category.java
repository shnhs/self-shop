package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  @EmbeddedId
  private CategoryId id;

  @Column(name = "name")
  private String name;

  public Category() {
  }

  public Category(CategoryId id, String name) {
    this.id = id;
    this.name = name;
  }

  public CategoryId id() {
    return id;
  }

  public String name() {
    return name;
  }
}
