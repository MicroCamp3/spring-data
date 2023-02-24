package com.comarch.bootcamp.jdbc.jpa.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
  private Long id;

  @NotNull
  @DecimalMin("0.01")
  private BigDecimal price;

  private Integer customerId;
  private int version;
}
