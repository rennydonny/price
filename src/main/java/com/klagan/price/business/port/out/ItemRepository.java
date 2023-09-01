package com.klagan.price.business.port.out;

import com.klagan.price.business.domain.Item;
import java.util.List;

public interface ItemRepository {

  List<Item> find(Integer productCode, Integer brandId);
}
