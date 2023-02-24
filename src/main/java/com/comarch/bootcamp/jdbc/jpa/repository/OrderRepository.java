package com.comarch.bootcamp.jdbc.jpa.repository;

import com.comarch.bootcamp.jdbc.jpa.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository
    extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
  @Query("SELECT o FROM Order o left join fetch o.customer")
  List<Order> findAllWithCustomers();

  @Override
  //    @EntityGraph(attributePaths = "customer")
  List<Order> findAll();
}
