package com.example.demo.models;

public class ImageId extends EntityId {

  public ImageId() {
  }

  public ImageId(String value) {
    super(value);
  }

  public ImageId generate() {
    return new ImageId(newTsid());
  }
}
