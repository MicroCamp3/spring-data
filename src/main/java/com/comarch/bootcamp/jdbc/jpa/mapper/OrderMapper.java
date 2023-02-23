package com.comarch.bootcamp.jdbc.jpa.mapper;

import com.comarch.bootcamp.jdbc.jpa.dto.OrderDto;
import com.comarch.bootcamp.jdbc.jpa.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDto, Order> {
    @Mapping(source = "customerId", target = "customer.id")
    Order toEntity(OrderDto dto);

    @Mapping(target = "customerId", source = "customer.id")
    OrderDto toDto(Order entnity);
}
