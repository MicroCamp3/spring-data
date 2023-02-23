package com.comarch.bootcamp.jdbc.jpa.mapper;

import com.comarch.bootcamp.jdbc.jpa.dto.CustomerDto;
import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {
}
