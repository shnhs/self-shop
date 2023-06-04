package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends BaseEntity {

  @EmbeddedId
  private ImageId id;

  @Column(name = "url")
  private String url;

  public Image() {
  }

  public Image(ImageId id, String url) {
    this.id = id;
    this.url = url;
  }

  public ImageId id() {
    return id;
  }

  public String url() {
    return url;
  }
}
