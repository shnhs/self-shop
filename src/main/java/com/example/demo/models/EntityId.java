package com.example.demo.models;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class EntityId {

  @Column(name = "id")
  private String value;

  public EntityId() {
  }

  public EntityId(String value) {
    this.value = value;
  }

  public String newTsid() {
    return TSID.Factory.getTsid()
                       .toString();
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntityId entityId = (EntityId) o;
    return Objects.equals(value, entityId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
