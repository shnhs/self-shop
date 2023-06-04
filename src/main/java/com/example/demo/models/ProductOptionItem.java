package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_option_items")
public class ProductOptionItem extends BaseEntity {

  @EmbeddedId
  private ProductOptionItemId id;

  @Column(name = "name")
  private String name;

  public ProductOptionItem() {
  }

  public ProductOptionItem(ProductOptionItemId id, String name) {
    this.id = id;
    this.name = name;
  }

  public ProductOptionItemId id() {
    return id;
  }

  public String name() {
    return name;
  }
}
