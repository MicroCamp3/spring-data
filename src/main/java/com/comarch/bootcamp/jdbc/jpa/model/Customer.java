package com.comarch.bootcamp.jdbc.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id @GeneratedValue Integer id;

  @Column(name = "name")
  String firstName;

  String lastName;

  @Column(unique = true)
  String customerKey;

  public Customer(Integer id) {
    this.id = id;
  }
}
