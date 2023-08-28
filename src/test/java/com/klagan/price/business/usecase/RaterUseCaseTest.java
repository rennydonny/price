package com.klagan.price.business.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.klagan.price.business.domain.Brand;
import com.klagan.price.business.domain.Currency;
import com.klagan.price.business.domain.Item;
import com.klagan.price.business.domain.Price;
import com.klagan.price.business.domain.Product;
import com.klagan.price.business.port.out.ItemRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RaterUseCaseTest {

  @Test
  void given14At10AmAndBrandZaraWhenGetFinalPriceThen() {
    LocalDateTime date = LocalDateTime.parse("2020-06-14-10.00.00", DateTimeFormatter.ofPattern(
        Item.ITEM_DATE_FORMAT));
    int productCode = 35455;
    int brandId = 1;
    Item item1 = Item.builder()
        .product(Product.builder()
            .brand(Brand.builder().identifier(1).name("ZARA").build())
            .identifier(35455).build())
        .identifier(1)
        .startDate(LocalDateTime.parse("2020-06-14-00.00.00",DateTimeFormatter.ofPattern(
            Item.ITEM_DATE_FORMAT)))
        .endDate(LocalDateTime.parse("2020-12-31-23.59.59", DateTimeFormatter.ofPattern(
            Item.ITEM_DATE_FORMAT)))
        .price(Price.builder().amount(BigDecimal.valueOf(35.50)).currency(Currency.EUR).build())
        .build();
    ItemRepository mock = Mockito.mock(ItemRepository.class);
    when(mock.find(productCode,brandId)).thenReturn(List.of(item1));
    RaterUseCase raterUseCase = new RaterUseCase(mock);
    Optional<Item> item = raterUseCase.getFinalPrice(date, productCode, brandId);
    assertFalse(item.isEmpty());
    assertEquals(1,item.get().getIdentifier());
  }
}
