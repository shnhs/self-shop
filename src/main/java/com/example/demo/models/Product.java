package com.example.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

  @EmbeddedId
  private ProductId id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "category_id"))
  private CategoryId categoryId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_id")
  @OrderBy("id")
  private List<Image> images = new ArrayList<>();

  @Column(name = "name")
  private String name;

  @Embedded
  @AttributeOverride(name = "amount", column = @Column(name = "price"))
  private Money price;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_id")
  @OrderBy("id")
  private List<ProductOption> options = new ArrayList<>();

  @Column(name = "description")
  private String description;

  public Product() {
  }

  public Product(ProductId id, CategoryId categoryId, List<Image> images, String name, Money price,
                 List<ProductOption> options, String description) {
    this.id = id;
    this.categoryId = categoryId;
    this.images = images;
    this.name = name;
    this.price = price;
    this.options = options;
    this.description = description;
  }

  public ProductId id() {
    return id;
  }

  public CategoryId categoryId() {
    return categoryId;
  }

  public List<Image> images() {
    return images;
  }

  public String name() {
    return name;
  }

  public Money price() {
    return price;
  }

  public List<ProductOption> options() {
    return options;
  }

  public int imageSize() {
    return images.size();
  }

  public String description() {
    return description;
  }

  public int optionSize() {
    return options.size();
  }

  public ProductOption option(int index) {
    return options.get(index);
  }
}
