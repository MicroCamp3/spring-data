package com.comarch.bootcamp.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import com.comarch.bootcamp.jdbc.jpa.model.Order;
import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import com.comarch.bootcamp.jdbc.jpa.repository.OrderRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private OrderRepository orderRepository;

  @Test
  void createOrderAndConnectToCustomer() {
    Customer savedCustomer =
        customerRepository.save(createCustomer("Krzystof", "Kowalski", "krzkow"));
    orderRepository.saveAndFlush(createOrder(savedCustomer));
  }

  @Test
  void createManyOrderAndConnectToCustomer() {
    Customer savedCustomer =
        customerRepository.save(createCustomer("Krzystof", "Kowalski", "krzkow"));

    long orders = orderRepository.count();
    orderRepository.saveAndFlush(createOrder(savedCustomer));
    orderRepository.saveAndFlush(createOrder(savedCustomer));
    orderRepository.saveAndFlush(createOrder(savedCustomer));

    assertThat(orderRepository.count()).isEqualTo(orders + 3);
  }

  public static Order createOrder(Customer savedCustomer) {
    return new Order(null, new BigDecimal("100.01"), savedCustomer);
  }

  private static Customer createCustomer(String name, String lastName, String key) {
    return new Customer(null, name, lastName, key);
  }
}
