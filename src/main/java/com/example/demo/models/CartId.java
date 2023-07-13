package com.example.demo.models;

public class CartId extends EntityId {
  public CartId() {
  }

  public CartId(String value) {
    super(value);
  }

  public CartId generate() {
    return new CartId(newTsid());
  }
}
