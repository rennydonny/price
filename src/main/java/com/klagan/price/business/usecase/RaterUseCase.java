package com.klagan.price.business.usecase;

import com.klagan.price.business.domain.Item;
import com.klagan.price.business.port.out.ItemRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RaterUseCase {

  private final ItemRepository item;

  public RaterUseCase(ItemRepository item) {
    this.item = item;
  }

  public Optional<Item> getFinalPrice(LocalDateTime date, int productCode, int brandId) {
    List<Item> items = item.find(productCode, brandId);
    Comparator<Item> descendingPriority = Comparator.
        comparing(Item::getPriority, Comparator.reverseOrder());
    return items.stream().filter(item -> item.isAvailable(date)).min(descendingPriority);
  }
}
