package com.klagan.price.infrastructure.mapper;

import static com.klagan.price.application.rest.pricing.domain.FinalPriceResponseDto.ITEM_DATETIME_FORMAT;
import static java.util.Objects.nonNull;

import com.klagan.price.application.rest.pricing.domain.FinalPriceResponseDto;
import com.klagan.price.business.domain.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {

  @Mapping(target = "identifier", source = "id")
  @Mapping(target = "price.amount", source = "price")
  @Mapping(target = "price.currency", source = "currency")
  @Mapping(target = "product.identifier", source = "product.id")
  @Mapping(target = "product.brand.identifier", source = "product.brand.id")
  @Mapping(target = "product.brand.name", source = "product.brand.name")
  public abstract Item fromEntity(
      com.klagan.price.infrastructure.repository.entities.Item entity);

  public abstract List<Item> toItems(
      List<com.klagan.price.infrastructure.repository.entities.Item> entity);

  @Mapping(target = "priceId", source = "identifier")
  @Mapping(target = "productId", source = "product.identifier")
  @Mapping(target = "brandId", source = "product.brand.identifier")
  @Mapping(target = "startDate", expression = "java(fixLocalDateFormat(item.getStartDate()))")
  @Mapping(target = "endDate", expression = "java(fixLocalDateFormat(item.getEndDate()))")
  @Mapping(target = "priceAmount", source = "price.amount")
  public abstract FinalPriceResponseDto toDto(Item item);

  protected String fixLocalDateFormat(LocalDateTime dateTime) {
    if(nonNull(dateTime)) {
      return dateTime.format(DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    }
    return null;
  }
}
