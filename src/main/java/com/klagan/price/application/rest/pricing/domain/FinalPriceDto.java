package com.klagan.price.application.rest.pricing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FinalPriceDto {
  private String dateTimeSearching;
  private Integer productCode;
  private Integer brandCode;
}
