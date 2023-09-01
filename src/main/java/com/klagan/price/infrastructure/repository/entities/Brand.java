package com.klagan.price.infrastructure.repository.entities;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="brands")
public class Brand {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
  private List<Product> products;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
  private List<Item> items;
}
