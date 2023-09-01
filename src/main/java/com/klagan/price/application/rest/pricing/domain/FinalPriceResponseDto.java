package com.klagan.price.application.rest.pricing.domain;

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
public class FinalPriceResponseDto {
  public static final String ITEM_DATETIME_FORMAT = "yyyy-MM-dd'-'HH.mm.ss";
  private Integer priceId;
  private Integer productId;
  private Integer brandId;
  private String startDate;
  private String endDate;
  private Float priceAmount;
}
