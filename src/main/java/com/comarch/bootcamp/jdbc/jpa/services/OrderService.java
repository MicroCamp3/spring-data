package com.comarch.bootcamp.jdbc.jpa.services;

import com.comarch.bootcamp.jdbc.jpa.config.ApplicationProperties;
import com.comarch.bootcamp.jdbc.jpa.dto.OrderDto;
import com.comarch.bootcamp.jdbc.jpa.mapper.OrderMapper;
import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import com.comarch.bootcamp.jdbc.jpa.model.Order;
import com.comarch.bootcamp.jdbc.jpa.model.Order_;
import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import com.comarch.bootcamp.jdbc.jpa.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;

  private final ApplicationProperties properties;
  private final OrderMapper orderMapper;

  private static Order createOrder(Customer savedCustomer) {
    return new Order(null, new BigDecimal("10.01"), savedCustomer);
  }

  private static Customer createCustomer(String name, String lastName, String key) {
    return new Customer(null, name, lastName, key);
  }

  private static Specification<Order> createSpecificationPremium() {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.greaterThan(root.get(Order_.price), new BigDecimal(100));
  }

  public OrderDto findById(Long id) {
    Optional<Order> order = orderRepository.findById(id);
    return order.map(orderMapper::toDto).orElseThrow(RuntimeException::new);
  }

  public List<OrderDto> findWithPremiumPrice(Pageable pageable) {
    Specification<Order> specificationPremium = createSpecificationPremium();

    if (properties.isDevMode()) {
      specificationPremium = specificationPremium.or(developerUser());
    }
    return orderRepository.findAll(specificationPremium, pageable).stream()
        .map(orderMapper::toDto)
        .toList();
  }

  private Specification<Order> developerUser() {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(Order_.customer), new Customer(properties.getPremiumUser()));
  }

  @PostConstruct
  public void init() {
    if (customerRepository.count() == 0) {
      var savedCustomer = customerRepository.save(createCustomer("Krzystof", "Kowalski", "krzkow"));

      var savedCustomer2 =
          customerRepository.save(createCustomer("Ziutek", "Ziutkowski", "ziuziu"));

      orderRepository.saveAndFlush(createOrder(savedCustomer));
      orderRepository.saveAndFlush(createOrder(savedCustomer2));
      orderRepository.saveAndFlush(createOrder(savedCustomer));
    }
  }

  public List<OrderDto> findAll(Pageable pageable) {
    return orderRepository.findAll(pageable).map(orderMapper::toDto).toList();
  }

  @Transactional
  public OrderDto create(OrderDto orderDto) {
    var orderEntity = orderMapper.toEntity(orderDto);
    var savedOrder = orderRepository.save(orderEntity);
    return orderMapper.toDto(savedOrder);
  }
}
