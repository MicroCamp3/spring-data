package com.comarch.bootcamp.jdbc.jpa.services;

import com.comarch.bootcamp.jdbc.jpa.dto.OrderDto;
import com.comarch.bootcamp.jdbc.jpa.repository.OrderRepository;
import com.comarch.bootcamp.jdbc.jpa.mapper.OrderMapper;
import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import com.comarch.bootcamp.jdbc.jpa.model.Order;
import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;

    private static Order createOrder(Customer savedCustomer) {
        return new Order(null, new BigDecimal("10.01"), savedCustomer);
    }

    private static Customer createCustomer(String name, String lastName, String key) {
        return new Customer(null,
                name, lastName, key);
    }

    public OrderDto findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order
                .map(orderMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @PostConstruct
    public void init() {
        Customer savedCustomer = customerRepository.save(createCustomer(
                "Krzystof", "Kowalski", "krzkow"));

        Customer savedCustomer2 = customerRepository.save(createCustomer(
                "Ziutek", "Ziutkowski", "ziuziu"));

        orderRepository.saveAndFlush(createOrder(savedCustomer));
        orderRepository.saveAndFlush(createOrder(savedCustomer2));
        orderRepository.saveAndFlush(createOrder(savedCustomer));
    }

    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    public OrderDto create(OrderDto orderDto) {
        Order orderEntity = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(orderEntity);
        return orderMapper.toDto(savedOrder);
    }
}
