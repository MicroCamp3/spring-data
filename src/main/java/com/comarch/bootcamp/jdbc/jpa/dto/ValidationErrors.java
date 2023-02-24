package com.comarch.bootcamp.jdbc.jpa.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationErrors {
  private List<String> errors;
}
