package com.comarch.bootcamp.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.comarch.bootcamp.jdbc.jpa.dto.OrderDto;
import com.comarch.bootcamp.jdbc.jpa.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

  @Autowired ObjectMapper objectMapper;

  @Autowired OrderRepository orderRepository;
  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturnListOrders() throws Exception {
    mockMvc
        .perform((get("/api/order")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.[*].id").value(hasItem(1)));
  }

  @Test
  void create() throws Exception {
    long count = orderRepository.count();
    mockMvc
        .perform(
            (post("/api/order"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsBytes(
                        new OrderDto(null, new BigDecimal("1.23"), 1, 0))))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.price").value(new BigDecimal("1.23")));
    assertThat(orderRepository.count()).isEqualTo(count + 1);
  }
}
