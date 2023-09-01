package com.klagan.price.business.usecase;

import static java.util.Objects.isNull;

import com.klagan.price.business.domain.Item;
import com.klagan.price.business.port.in.FinalPricingFinderPort;
import com.klagan.price.business.port.out.ItemRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class FinalPricingUseCase implements FinalPricingFinderPort {
  private final ItemRepository item;
  public FinalPricingUseCase(ItemRepository item) {
    this.item = item;
  }

  public Optional<Item> findBy(LocalDateTime dateTime, Integer brandCode, Integer productCode) {
    if (isNull(dateTime) || isNull(brandCode) || isNull(productCode)) {
      log.warn("¡Required parameters are null!");
      return Optional.empty();
    }
    List<Item> items = this.getItem().find(productCode, brandCode);
    Comparator<Item> descendingPriority = Comparator.
        comparing(Item::getPriority, Comparator.reverseOrder());
    return items.stream().filter(item -> item.isCurrent(dateTime)).min(descendingPriority);
  }
}
