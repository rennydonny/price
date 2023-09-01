package com.klagan.price.business.port.in;

import com.klagan.price.business.domain.Item;
import java.time.LocalDateTime;
import java.util.Optional;

public interface FinalPricingFinderPort {
  Optional<Item> findBy(LocalDateTime dateTime, Integer brandCode, Integer productCode);
}
