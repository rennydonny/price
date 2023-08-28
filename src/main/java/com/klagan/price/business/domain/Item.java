package com.klagan.price.business.usecase;

import java.time.LocalDate;
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
  private int identifier;
  private Brand brand;
  private LocalDate startDate;
  private LocalDate endDate;
}
