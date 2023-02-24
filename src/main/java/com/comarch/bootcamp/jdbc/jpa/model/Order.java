package com.comarch.bootcamp.jdbc.jpa.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "customer_order")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {
  @Version int version;
  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  private Customer customer;

  @LastModifiedDate private Instant lastModifyDate;

  @CreatedDate
  @Column(updatable = false)
  private Instant createdDate;

  @CreatedBy
  @Column(updatable = false)
  private String cratedBy;

  @LastModifiedBy private String modifiedBy;

  public Order(Long id, BigDecimal price, Customer customer) {
    this.id = id;
    this.price = price;
    this.customer = customer;
  }
}
