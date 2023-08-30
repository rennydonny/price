package com.klagan.price.business.usecase;

import static com.klagan.price.business.domain.Item.ITEM_DATETIME_FORMAT;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FinalPricingRaterUseCaseTest {

  private static FinalPricingRaterUseCase pricingRater;

  @BeforeAll
  static void beforeAll() {
    ItemRepository mock = Mockito.mock(ItemRepository.class);
    pricingRater = new FinalPricingRaterUseCase(mock);
    when(mock.find(35455, 1)).thenReturn(
        List.of(
            buildZaraProductItem(1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", 35.50, 0),
            buildZaraProductItem(2, "2020-06-14-15.00.00", "2020-06-14-18.30.00", 25.45, 1),
            buildZaraProductItem(3, "2020-06-15-00.00.00", "2020-06-15-11.00.00", 30.50, 1),
            buildZaraProductItem(4, "2020-06-15-16.00.00", "2020-12-31-23.59.59", 38.95, 1)));
  }

  @Test
  void givenJune14At10HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListOne() {
    LocalDateTime date = LocalDateTime.parse("2020-06-14-10.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertFalse(itemFound.isEmpty());
    assertEquals(1, itemFound.get().getIdentifier());
    assertEquals(0, itemFound.get().getPriority());
  }

  @Test
  void givenJune14At16HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListTwo() {
    LocalDateTime date = LocalDateTime.parse("2020-06-14-16.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertFalse(itemFound.isEmpty());
    assertEquals(2, itemFound.get().getIdentifier());
    assertEquals(1, itemFound.get().getPriority());
  }

  @Test
  void givenJune14At21HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListTwo() {
    LocalDateTime date = LocalDateTime.parse("2020-06-14-21.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertFalse(itemFound.isEmpty());
    assertEquals(1, itemFound.get().getIdentifier());
    assertEquals(0, itemFound.get().getPriority());
  }

  @Test
  void givenJune15At10HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListThree() {
    LocalDateTime date = LocalDateTime.parse("2020-06-15-10.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertFalse(itemFound.isEmpty());
    assertEquals(3, itemFound.get().getIdentifier());
    assertEquals(1, itemFound.get().getPriority());
  }

  @Test
  void givenJune16At21HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListFour() {
    LocalDateTime date = LocalDateTime.parse("2020-06-16-21.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertFalse(itemFound.isEmpty());
    assertEquals(4, itemFound.get().getIdentifier());
    assertEquals(1, itemFound.get().getPriority());
  }

  @Test
  void givenAny2023DateWhenSearchingFinalPriceForZaraProductThenEmptyResults() {
    LocalDateTime date = LocalDateTime.parse("2023-06-16-21.00.00",
        DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    int productCode = 35455;
    int brandId = 1;

    Optional<Item> itemFound = pricingRater.findBy(date, productCode, brandId);

    assertTrue(itemFound.isEmpty());
  }

  private static Item buildZaraProductItem(int priceListId, String startDate, String endDate,
      double priceAmount, int priority) {
    return Item.builder()
        .product(Product.builder()
            .brand(Brand.builder().identifier(1).name("ZARA").build())
            .identifier(35455).build())
        .identifier(priceListId)
        .startDate(LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern(
            ITEM_DATETIME_FORMAT)))
        .endDate(LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(
            ITEM_DATETIME_FORMAT)))
        .price(
            Price.builder().amount(BigDecimal.valueOf(priceAmount)).currency(Currency.EUR).build())
        .priority(priority)
        .build();
  }
}
