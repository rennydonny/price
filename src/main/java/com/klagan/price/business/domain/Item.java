package com.klagan.price.business.domain;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

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

  public static final String ITEM_DATETIME_FORMAT = "yyyy-MM-dd'-'HH.mm.ss";
  private int identifier;
  private Product product;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Price price;
  private int priority;

  public boolean isCurrent(LocalDateTime date) {
    return isCurrentDate(date) && isCurrentTime(date);
  }

  private boolean isCurrentTime(LocalDateTime date) {
    return
        (this.startDate.truncatedTo(HOURS).isBefore(date.truncatedTo(HOURS))
            || this.startDate.truncatedTo(HOURS).isEqual(date.truncatedTo(HOURS)))
            &&
            (this.endDate.truncatedTo(HOURS).isAfter(date.truncatedTo(HOURS))
                || this.endDate.truncatedTo(HOURS).isEqual(date.truncatedTo(HOURS)));
  }

  private boolean isCurrentDate(LocalDateTime date) {
    return
        (this.startDate.truncatedTo(DAYS).isBefore(date.truncatedTo(DAYS))
            || this.startDate.truncatedTo(DAYS).isEqual(date.truncatedTo(DAYS)))
            &&
            (this.endDate.truncatedTo(DAYS).isAfter(date.truncatedTo(DAYS))
                || this.endDate.truncatedTo(DAYS).isEqual(date.truncatedTo(DAYS)));
  }
}
