package com.klagan.price.business.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

  public static final String ITEM_DATE_FORMAT = "yyyy-MM-dd'-'HH.mm.ss";
  private int identifier;
  private Product product;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Price price;
  private int priority;

  public boolean isAvailable(LocalDateTime date) {
    return true;
  }
}
