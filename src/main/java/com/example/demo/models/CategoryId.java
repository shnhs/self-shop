package com.example.demo.models;

public class CategoryId extends EntityId {

  public CategoryId() {
  }

  public CategoryId(String value) {
    super(value);
  }

  public CategoryId generate() {
    return new CategoryId(newTsid());
  }
}
