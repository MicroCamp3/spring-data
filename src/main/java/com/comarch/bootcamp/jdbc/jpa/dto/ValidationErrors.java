package com.comarch.bootcamp.jdbc.jpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrors {
   private List<String> errors;
}
