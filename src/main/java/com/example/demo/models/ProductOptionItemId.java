package com.example.demo.models;

public class ProductOptionItemId extends EntityId {

  public ProductOptionItemId() {
  }

  public ProductOptionItemId(String value) {
    super(value);
  }

  public ProductOptionItemId generate() {
    return new ProductOptionItemId(newTsid());
  }
}
