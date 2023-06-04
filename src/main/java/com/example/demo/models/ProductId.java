package com.example.demo.models;

public class ProductId extends EntityId {

  public ProductId() {
  }

  public ProductId(String value) {
    super(value);
  }

  public ProductId generate() {
    return new ProductId(newTsid());
  }
}
