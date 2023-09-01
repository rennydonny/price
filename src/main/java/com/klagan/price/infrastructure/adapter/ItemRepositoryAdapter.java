package com.klagan.price.infrastructure.adapter;

import com.klagan.price.business.domain.Item;
import com.klagan.price.business.port.out.ItemRepository;
import com.klagan.price.infrastructure.mapper.ItemMapper;
import com.klagan.price.infrastructure.repository.ItemProvider;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ItemRepositoryAdapter implements ItemRepository {

  private final ItemProvider provider;
  private final ItemMapper mapper;

  public ItemRepositoryAdapter(ItemProvider provider, ItemMapper mapper) {
    this.provider = provider;
    this.mapper = mapper;
  }

  @Override
  public List<Item> find(Integer productCode, Integer brandId) {
    return this.getMapper()
        .toItems(this.getProvider().findByBrandIdAndProductId(Long.valueOf(brandId),
            Long.valueOf(productCode)));
  }
}
