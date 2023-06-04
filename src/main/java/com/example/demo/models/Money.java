package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Money {

  @Column(name = "amount")
  private Long amount;

  public Money() {
  }

  public Money(Long amount) {
    this.amount = amount;
  }

  public Long asLong() {
    return amount;
  }

  public String toString() {
    return amount.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount, money.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }
}
