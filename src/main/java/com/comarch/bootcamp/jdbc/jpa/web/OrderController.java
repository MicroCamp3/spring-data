package com.comarch.bootcamp.jdbc.jpa.web;

import com.comarch.bootcamp.jdbc.jpa.dto.OrderDto;
import com.comarch.bootcamp.jdbc.jpa.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @RequestMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

   /* @RequestMapping
    public List<OrderDto> listOrders() {
        return orderService.findAll();
    }*/

    @RequestMapping("")
    public List<OrderDto> listOrders(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @PostMapping(value = "")
    public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }


    @PostMapping(value = "{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }
}
