package com.klagan.price.application.rest.pricing;

import static com.klagan.price.application.rest.pricing.domain.FinalPriceResponseDto.ITEM_DATETIME_FORMAT;
import static java.util.Objects.isNull;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.klagan.price.application.rest.pricing.domain.FinalPriceDto;
import com.klagan.price.application.rest.pricing.domain.FinalPriceResponseDto;
import com.klagan.price.business.port.in.FinalPricingFinderPort;
import com.klagan.price.infrastructure.mapper.ItemMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RestController
@Slf4j
@RequestMapping("/items")
public class FinalPriceController {

  private final FinalPricingFinderPort finalPricingFinderPort;
  private final ItemMapper mapper;

  public FinalPriceController(FinalPricingFinderPort finalPricingFinderPort, ItemMapper mapper) {
    this.finalPricingFinderPort = finalPricingFinderPort;
    this.mapper = mapper;
  }

  @PostMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
  public FinalPriceResponseDto getFinalPricing(@RequestBody FinalPriceDto request) {
    try {
      validate(request);
      return this.getFinalPricingFinderPort()
          .findBy(LocalDateTime.parse(request.getDateTimeSearching(),
                  DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT)), request.getBrandCode(),
              request.getProductCode()).map(mapper::toDto)
          .orElseThrow(
              () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Final price not found",
                  null));
    } catch (Exception ex) {
      log.error("¡Error getting final price! For class {} . With parameters {} ", this.getClass(),
          request.toString());
      throw ex;
    }
  }

  private void validate(FinalPriceDto dto) {
    String dateTimeSearching = dto.getDateTimeSearching();
    if (isNull(dateTimeSearching) || isNull(dto.getBrandCode()) || isNull(
        dto.getProductCode())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Required parameters are null: dateTimeSearching, productCode, brandCode.",
          null);
    }
    try {
      LocalDateTime.parse(dateTimeSearching, DateTimeFormatter.ofPattern(ITEM_DATETIME_FORMAT));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(
          "Invalid datetime format. Consider to format as: %s.", ITEM_DATETIME_FORMAT),
          null);
    }
  }

}
