package com.comarch.bootcamp.jdbc.jpa.repository;

import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstName(String firstName);
    Page<Customer> findByFirstName(String firstName, Pageable page);

}
