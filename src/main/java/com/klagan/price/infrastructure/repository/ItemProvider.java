package com.klagan.price.infrastructure.repository;

import com.klagan.price.infrastructure.repository.entities.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProvider extends JpaRepository<Item, Long> {
  List<Item> findByBrandIdAndProductId(Long brandId, Long productId);
}
