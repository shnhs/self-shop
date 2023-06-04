package com.example.demo.models;

public class ProductOptionId extends EntityId {

  public ProductOptionId() {
  }

  public ProductOptionId(String value) {
    super(value);
  }

  public ProductOptionId generate() {
    return new ProductOptionId(newTsid());
  }
}
