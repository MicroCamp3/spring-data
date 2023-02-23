package com.comarch.bootcamp.jdbc;

import com.comarch.bootcamp.jdbc.jpa.repository.OrderRepository;
import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import com.comarch.bootcamp.jdbc.jpa.model.Order;
import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void createOrderAndConnectToCustomer() {
        Customer savedCustomer = customerRepository.save(createCustomer(
                "Krzystof", "Kowalski", "krzkow"));
        orderRepository.saveAndFlush(createOrder(savedCustomer));

    }

    @Test
    void createManyOrderAndConnectToCustomer() {
        Customer savedCustomer = customerRepository.save(createCustomer(
                "Krzystof", "Kowalski", "krzkow"));

        orderRepository.saveAndFlush(createOrder(savedCustomer));
        orderRepository.saveAndFlush(createOrder(savedCustomer));
        orderRepository.saveAndFlush(createOrder(savedCustomer));


        orderRepository.findById(1L).get().getCustomer();
        System.out.println();
    }

    private static Order createOrder(Customer savedCustomer) {
        return new Order(null, new BigDecimal("10.01"), savedCustomer);
    }

    private static Customer createCustomer(String name, String lastName, String key) {
        return new Customer(null,
                name, lastName, key);
    }
}
