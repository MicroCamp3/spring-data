package com.comarch.bootcamp.jdbc.jpa.repository;

import com.comarch.bootcamp.jdbc.jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o left join fetch o.customer")
    List<Order> findAllWithCustomers();

    @Override
//    @EntityGraph(attributePaths = "customer")
    List<Order> findAll();
}
