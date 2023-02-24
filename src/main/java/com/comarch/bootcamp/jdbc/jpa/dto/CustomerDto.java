package com.comarch.bootcamp.jdbc.jpa.dto;

import lombok.Data;

@Data
public class CustomerDto {
  private Integer id;
  private String firstName;
  private String lastName;
  private String customerKey;
}
