package com.klagan.price.application.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.klagan.price.PriceApplication;
import com.klagan.price.application.rest.pricing.domain.FinalPriceDto;
import com.klagan.price.application.rest.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PriceApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class FinalPricingIntegrationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void givenJune14At10HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListOne()
      throws Exception {
    FinalPriceDto dto = buildDto("2020-06-14-10.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.priceId", is(1)))
        .andExpect(jsonPath("$.productId", is(35455)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2020-06-14-00.00.00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31-23.59.59")))
        .andExpect(jsonPath("$.priceAmount", is(35.5)));
  }

  @Test
  void givenJune14At16HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListTwo()
      throws Exception {
    FinalPriceDto dto = buildDto("2020-06-14-16.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.priceId", is(2)))
        .andExpect(jsonPath("$.productId", is(35455)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2020-06-14-15.00.00")))
        .andExpect(jsonPath("$.endDate", is("2020-06-14-18.30.00")))
        .andExpect(jsonPath("$.priceAmount", is(25.45)));
  }

  @Test
  void givenJune14At21HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListTwo()
      throws Exception {
    FinalPriceDto dto = buildDto("2020-06-14-21.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.priceId", is(1)))
        .andExpect(jsonPath("$.productId", is(35455)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2020-06-14-00.00.00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31-23.59.59")))
        .andExpect(jsonPath("$.priceAmount", is(35.5)));
  }

  @Test
  void givenJune15At10HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListThree()
      throws Exception {
    FinalPriceDto dto = buildDto("2020-06-15-10.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.priceId", is(3)))
        .andExpect(jsonPath("$.productId", is(35455)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2020-06-15-00.00.00")))
        .andExpect(jsonPath("$.endDate", is("2020-06-15-11.00.00")))
        .andExpect(jsonPath("$.priceAmount", is(30.5)));
  }

  @Test
  void givenJune16At21HoursWhenSearchingFinalPriceForZaraProductThenGetPriceListFour()
      throws Exception {
    FinalPriceDto dto = buildDto("2020-06-16-21.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.priceId", is(4)))
        .andExpect(jsonPath("$.productId", is(35455)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2020-06-15-16.00.00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31-23.59.59")))
        .andExpect(jsonPath("$.priceAmount", is(38.95)));
  }

  @Test
  void givenAny2023DateWhenSearchingFinalPriceForZaraProductThenNotFoundException()
      throws Exception {
    FinalPriceDto dto = buildDto("2023-06-16-21.00.00");
    mvc.perform(post("/items/search").contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(dto)))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  private FinalPriceDto buildDto(String date) {
    return FinalPriceDto.builder().dateTimeSearching(date).brandCode(1)
        .productCode(35455).build();
  }
}
